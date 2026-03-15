package io.github.xingray.volcengine_kotlin_sdk_ark.util

import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.name
import java.io.File

actual suspend fun PlatformFile.saveToTempFile(): String {
    val tempDir = System.getProperty("java.io.tmpdir")
    val tempFile = File(tempDir, this.name)
    this.file.copyTo(tempFile, overwrite = true)
    return tempFile.absolutePath
}
