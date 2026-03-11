package io.github.xingray.volcengine_kotlin_sdk_ark

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform