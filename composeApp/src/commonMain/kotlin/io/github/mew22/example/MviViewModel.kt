package io.github.mew22.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

@Suppress("TooGenericExceptionCaught")
inline fun <T, R> T.runCatchingSafe(block: T.() -> R): Result<R> = try {
    Result.success(block())
} catch (e: CancellationException) {
    throw e
} catch (e: Throwable) {
    Result.failure(e)
}

fun <T> Flow<T>.throttleFirst(periodMillis: Long): Flow<T> {
    require(periodMillis > 0) { "period should be positive" }
    return flow {
        var lastTime = 0L
        collect { value ->
            val currentTime = Clock.System.now().toEpochMilliseconds()
            if (currentTime - lastTime >= periodMillis) {
                lastTime = currentTime
                emit(value)
            }
        }
    }
}

@Suppress("UnnecessaryAbstractClass")
abstract class MviViewModel<Event, State>(
    defaultState: State,
) : ViewModel() {

    protected val events = MutableSharedFlow<Event>()

    protected val internalState = MutableStateFlow(defaultState)
    val state: StateFlow<State> get() = internalState

    fun dispatch(event: Event) {
        viewModelScope.launch { events.emit(event) }
    }

    protected inline fun <reified SpecificEvent : Event> on(
        crossinline handle: suspend (event: SpecificEvent) -> Unit,
    ) {
        events.filterIsInstance<SpecificEvent>()
            .map { runCatchingSafe { handle(it) } }
            .map { it.exceptionOrNull() }
            .filterNotNull()
            .onEach(this::onError)
            .launchIn(viewModelScope)
    }

    protected inline fun <reified SpecificEvent : Event> onClick(
        crossinline handle: suspend (event: SpecificEvent) -> Unit,
    ) {
        events.filterIsInstance<SpecificEvent>()
            .throttleFirst(CLICK_DEBOUNCE_TIME_MS)
            .map { runCatchingSafe { handle(it) } }
            .map { it.exceptionOrNull() }
            .filterNotNull()
            .onEach(this::onError)
            .launchIn(viewModelScope)
    }

    protected open fun onError(throwable: Throwable) {
        // Monitoring
    }

    companion object {
        const val CLICK_DEBOUNCE_TIME_MS = 300L
    }
}
