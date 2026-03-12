package io.github.xingray.volcengine_kotlin_sdk_ark

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() {
    // 设置控制台输出编码为 UTF-8
    System.setProperty("file.encoding", "UTF-8")
    System.setProperty("sun.stdout.encoding", "UTF-8")
    System.setProperty("sun.stderr.encoding", "UTF-8")

    // 强制设置标准输出流为 UTF-8
    System.setOut(java.io.PrintStream(System.out, true, Charsets.UTF_8))
    System.setErr(java.io.PrintStream(System.err, true, Charsets.UTF_8))

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "volcengine_kotlin_sdk_ark",
        ) {
            App()
        }
    }
}