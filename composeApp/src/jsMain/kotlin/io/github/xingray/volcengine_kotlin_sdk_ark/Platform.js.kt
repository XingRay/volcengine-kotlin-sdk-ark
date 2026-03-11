package io.github.xingray.volcengine_kotlin_sdk_ark

class JsPlatform : Platform {
    override val name: String = "Web with Kotlin/JS"
}

actual fun getPlatform(): Platform = JsPlatform()