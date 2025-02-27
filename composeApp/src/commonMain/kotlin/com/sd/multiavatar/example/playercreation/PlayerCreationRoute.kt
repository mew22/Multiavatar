package io.github.mew22.example.playercreation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object PlayerCreationRoute

fun NavController.toPlayerCreation(navOptions: NavOptions? = null) {
    navigate(PlayerCreationRoute, navOptions)
}

fun NavGraphBuilder.playerCreationScreen() {
    composable<PlayerCreationRoute> {
        val viewModel = koinViewModel<PlayerCreationViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        PlayerCreationScreen(
            state = state,
            dispatch = viewModel::dispatch,
        )
    }
}
