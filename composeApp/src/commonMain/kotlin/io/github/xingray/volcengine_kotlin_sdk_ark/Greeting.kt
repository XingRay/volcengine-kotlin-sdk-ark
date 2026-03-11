package io.github.xingray.volcengine_kotlin_sdk_ark

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}