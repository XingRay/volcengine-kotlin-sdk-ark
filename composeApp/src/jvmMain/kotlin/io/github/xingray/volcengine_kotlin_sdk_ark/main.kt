package io.github.xingray.volcengine_kotlin_sdk_ark

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "volcengine_kotlin_sdk_ark",
    ) {
        App()
    }
}