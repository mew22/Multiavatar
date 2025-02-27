package io.github.mew22.example

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import org.koin.compose.KoinApplication

@Suppress("ModifierMissing")
@Composable
fun App() {
    KoinApplication(
        application = koinConfig
    ) {
        MaterialTheme {
            AppNavHost()
        }
    }
}
