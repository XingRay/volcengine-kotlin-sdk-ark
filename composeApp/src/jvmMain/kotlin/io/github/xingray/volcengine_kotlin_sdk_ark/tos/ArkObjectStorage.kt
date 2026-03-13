package io.github.xingray.volcengine_kotlin_sdk_ark.tos

import com.volcengine.tos.TOSV2
import com.volcengine.tos.TOSV2ClientBuilder
import com.volcengine.tos.TosException
import com.volcengine.tos.model.bucket.CreateBucketV2Input
import com.volcengine.tos.model.bucket.CreateBucketV2Output
import com.volcengine.tos.model.`object`.DeleteObjectInput
import com.volcengine.tos.model.`object`.GetObjectV2Input
import com.volcengine.tos.model.`object`.GetObjectV2Output
import com.volcengine.tos.model.`object`.PutObjectInput
import io.github.xingray.volcengine_kotlin_sdk_ark.ApiResult
import org.slf4j.LoggerFactory
import java.io.*
import java.nio.charset.StandardCharsets


class ArkObjectStorage(
    accessKey: String = "your access key",
    secretKey: String = "your secret key",
    region: String = "cn-beijing",
    endpoint: String = "tos-cn-beijing.volces.com",
) {

    companion object {
        private val log = LoggerFactory.getLogger(ArkObjectStorage::class.java)
    }

    val client: TOSV2 = TOSV2ClientBuilder().build(region, endpoint, accessKey, secretKey)

    fun createBuket(name: String): ApiResult<CreateBucketV2Output> {
        val input = CreateBucketV2Input().setBucket(name)
        try {
            val output = client.createBucket(input)
            log.debug("Created bucket location is " + output.location)
            return ApiResult.Success(output)
        } catch (e: TosException) {
            log.error("Create bucket failed")
            e.printStackTrace()
            return ApiResult.error(e)
        }
    }

    fun deleteBucket(name: String): ApiResult<Unit> {
        return ApiResult.notImplemented()
    }

    fun putObject(bucket: String, key: String, value: ByteArray): ApiResult<Unit> {
        val stream: InputStream = ByteArrayInputStream(value)
        return putObject(bucket, key, stream)
    }

    fun putObject(bucket: String, key: String, value: String): ApiResult<Unit> {
        val stream: InputStream = ByteArrayInputStream(value.toByteArray(StandardCharsets.UTF_8))
        return putObject(bucket, key, stream)
    }

    fun putObject(bucket: String, key: String, file: File): ApiResult<Unit> {
        return file.inputStream().use {
            putObject(bucket, key, file.inputStream())
        }
    }

    fun putObject(bucket: String, key: String, content: InputStream): ApiResult<Unit> {
        try {
            val input = PutObjectInput().setBucket(bucket).setKey(key).setContent(content)
            val output = client.putObject(input)
            log.debug("Put object success, the object's etag is " + output.etag)
            return ApiResult.Success(Unit)
        } catch (e: TosException) {
            log.error("Put object failed")
            e.printStackTrace()
            return ApiResult.error(e)
        }
    }

    fun deleteObject(bucket: String, key: String): ApiResult<Unit> {
        val input = DeleteObjectInput().setBucket(bucket).setKey(key)
        try {
            val output = client.deleteObject(input)
            log.debug("Delete success, " + output)
            return ApiResult.Success(Unit)
        } catch (e: TosException) {
            log.error("Delete failed")
            e.printStackTrace()
            return ApiResult.error(e)
        }
    }

    fun getObject(bucket: String, key: String, outputStream: OutputStream): ApiResult<Unit> {
        val input = GetObjectV2Input().setBucket(bucket).setKey(key)
        try {
            val output: GetObjectV2Output = client.getObject(input)
            output.content.use { it.transferTo(outputStream) }
            return ApiResult.success(Unit)
        } catch (e: TosException) {
            log.error("getObject error")
            e.printStackTrace()
            return ApiResult.error(e)
        } catch (e: IOException) {
            log.error("getObject error")
            e.printStackTrace()
            return ApiResult.error(e)
        }
    }

    fun getObjectAsText(bucket: String, key: String): ApiResult<String> {
        val input = GetObjectV2Input().setBucket(bucket).setKey(key)
        try {
            val output: GetObjectV2Output = client.getObject(input)
            return ApiResult.success(output.content.readAllBytes().toString(StandardCharsets.UTF_8))
        } catch (e: TosException) {
            log.error("getObject error")
            e.printStackTrace()
            return ApiResult.error(e)
        } catch (e: IOException) {
            log.error("getObject error")
            e.printStackTrace()
            return ApiResult.error(e)
        }
    }
}
