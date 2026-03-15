package io.github.xingray.volcengine_kotlin_sdk_ark.util

import io.github.vinceglb.filekit.PlatformFile

expect suspend fun PlatformFile.saveToTempFile(): String