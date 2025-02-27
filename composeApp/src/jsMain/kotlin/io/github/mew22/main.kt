package io.github.mew22

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.navigation.ExperimentalBrowserHistoryApi
import androidx.navigation.bindToNavigation
import androidx.navigation.compose.rememberNavController
import io.github.mew22.example.App
import kotlinx.browser.window
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class, ExperimentalBrowserHistoryApi::class)
fun main() {
    onWasmReady {
        ComposeViewport(viewportContainerId = "composeApplication") {
            App()
//            val navController = rememberNavController()
//            LaunchedEffect(Unit) {
//                window.bindToNavigation(navController)
//            }
        }
    }
}