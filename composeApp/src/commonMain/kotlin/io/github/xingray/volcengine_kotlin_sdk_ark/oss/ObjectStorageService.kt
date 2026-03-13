package io.github.xingray.volcengine_kotlin_sdk_ark.oss

import io.github.xingray.volcengine_kotlin_sdk_ark.ApiResult

expect class ObjectStorageService(ak: String, sk: String) {
    fun putObject(bucket: String, key: String, filePath: String): ApiResult<String>
}