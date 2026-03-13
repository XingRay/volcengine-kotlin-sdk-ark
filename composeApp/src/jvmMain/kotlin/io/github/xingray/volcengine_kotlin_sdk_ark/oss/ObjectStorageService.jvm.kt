package io.github.xingray.volcengine_kotlin_sdk_ark.oss

import io.github.xingray.volcengine_kotlin_sdk_ark.ApiResult
import io.github.xingray.volcengine_kotlin_sdk_ark.isError
import io.github.xingray.volcengine_kotlin_sdk_ark.tos.ArkObjectStorage
import java.io.File

actual class ObjectStorageService actual constructor(ak: String, sk: String) {
    private val arkObjectStorage = ArkObjectStorage(ak, sk)

    actual fun putObject(bucket: String, key: String, filePath: String): ApiResult<String> {
        val result = arkObjectStorage.putObject(bucket, key, File(filePath))
        if (result.isError()) {
            return result.toApiError()
        }
        return ApiResult.success("https://${bucket}.tos-cn-beijing.volces.com/$key")
    }
}