package com.volcengine.ark.runtime.utils

import okhttp3.MediaType

object MultipartBodyUtils {
    val TYPE: MediaType? = MediaType.parse("multipart/form-data")
    val JSON: MediaType? = MediaType.parse("application/json; charset=utf-8")

    fun getPart(image: File, name: String?): MultipartBody.Part {
        val body: RequestBody? = RequestBody.create(com.volcengine.ark.runtime.utils.MultipartBodyUtils.TYPE, image)
        return MultipartBody.Part.createFormData(name, image.getName(), body)
    }
}
