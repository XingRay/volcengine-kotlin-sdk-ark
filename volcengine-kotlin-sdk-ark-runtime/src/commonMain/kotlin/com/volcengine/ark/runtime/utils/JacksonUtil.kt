package com.volcengine.ark.runtime.utils

import com.fasterxml.jackson.annotation.JsonInclude

object JacksonUtil {
    private val objectMapper: ObjectMapper = com.volcengine.ark.runtime.utils.JacksonUtil.defaultObjectMapper()

    private fun defaultObjectMapper(): ObjectMapper {
        val mapper: ObjectMapper = ObjectMapper()
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
        return mapper
    }

    fun clsToJsonNode(o: Object?): JsonNode {
        return com.volcengine.ark.runtime.utils.JacksonUtil.objectMapper.valueToTree(o)
    }

    fun <T> jsonNodeToCls(j: JsonNode?, cls: Class<T?>?): T? {
        return com.volcengine.ark.runtime.utils.JacksonUtil.objectMapper.convertValue(j, cls)
    }
}
