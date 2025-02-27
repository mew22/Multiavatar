package io.github.mew22

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.mew22.example.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "AllTargetExceptServer",
    ) {
        App()
    }
}