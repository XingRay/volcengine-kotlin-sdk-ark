package com.volcengine.ark.runtime.utils

import com.fasterxml.jackson.databind.ObjectMapper

object ResponseDecryptUtil {
    private val mapper: ObjectMapper = ObjectMapper()

    /**
     * 解密流式数据块
     */
    @Throws(Exception::class)
    fun decryptStreamChunk(data: String?, key: ByteArray?, nonce: ByteArray?): String? {
        try {
            @SuppressWarnings("unchecked") val chunkData: Map<String?, Object?> = com.volcengine.ark.runtime.utils.ResponseDecryptUtil.mapper.readValue(data, Map::class.java)

            if (!com.volcengine.ark.runtime.utils.ResponseDecryptUtil.hasValidChoices(chunkData)) {
                return com.volcengine.ark.runtime.utils.ResponseDecryptUtil.mapper.writeValueAsString(chunkData)
            }

            @SuppressWarnings("unchecked") val choices: List<Map<String?, Object?>?> = chunkData.get("choices") as List<Map<String?, Object?>?>

            for (choice in choices) {
                com.volcengine.ark.runtime.utils.ResponseDecryptUtil.decryptStreamChoiceContent(choice, key, nonce)
            }

            return com.volcengine.ark.runtime.utils.ResponseDecryptUtil.mapper.writeValueAsString(chunkData)
        } catch (e: Exception) {
            // 解密失败时返回原始数据
            return data
        }
    }

    /**
     * 检查chunk数据是否包含有效的choices
     */
    fun hasValidChoices(chunkData: Map<String?, Object?>): Boolean {
        return chunkData.containsKey("choices") && chunkData.get("choices") is List
    }

    /**
     * 解密流式choice的内容
     */
    fun decryptStreamChoiceContent(choice: Map<String?, Object?>, key: ByteArray?, nonce: ByteArray?) {
        if (!com.volcengine.ark.runtime.utils.ResponseDecryptUtil.shouldDecryptStreamChoice(choice)) {
            return
        }

        val encryptedContent: String? = com.volcengine.ark.runtime.utils.ResponseDecryptUtil.getEncryptedContentFromStreamChoice(choice)
        if (encryptedContent == null || encryptedContent.isEmpty()) {
            return
        }

        try {
            val decryptedContent: String? = KeyAgreementUtil.decryptStringWithKey(key, nonce, encryptedContent)
            com.volcengine.ark.runtime.utils.ResponseDecryptUtil.updateStreamChoiceContent(choice, decryptedContent)
        } catch (e: Exception) {
            com.volcengine.ark.runtime.utils.ResponseDecryptUtil.updateStreamChoiceContent(choice, "")
        }
    }

    /**
     * 检查是否应该解密流式choice
     */
    fun shouldDecryptStreamChoice(choice: Map<String?, Object?>): Boolean {
        if (!choice.containsKey("delta") || choice.get("delta") !is Map) {
            return false
        }
        @SuppressWarnings("unchecked") val delta: Map<String?, Object?>? = choice.get("delta") as Map<String?, Object?>?
        val finishReason = choice.get("finish_reason") as String?
        if ("content_filter".equals(finishReason)) {
            return false
        }
        if (delta == null || delta.isEmpty()) {
            return false
        }
        return delta.containsKey("content")
    }

    /**
     * 从流式choice中获取加密内容
     */
    fun getEncryptedContentFromStreamChoice(choice: Map<String?, Object?>): String? {
        @SuppressWarnings("unchecked") val delta: Map<String?, Object?>? = choice.get("delta") as Map<String?, Object?>?
        if (delta == null) {
            return null
        }
        val contentObj: Object? = delta.get("content")
        return if (contentObj is String) contentObj as String? else null
    }

    /**
     * 更新流式choice的内容
     */
    fun updateStreamChoiceContent(choice: Map<String?, Object?>, content: String?) {
        @SuppressWarnings("unchecked") val delta: Map<String?, Object?>? = choice.get("delta") as Map<String?, Object?>?
        if (delta != null) {
            delta.put("content", content)
        }
    }

    /**
     * 判断choice是否需要解密 - 非流式
     */
    fun shouldDecryptChoice(choice: Map<String?, Object?>): Boolean {
        if (!choice.containsKey("message") || choice.get("message") !is Map) {
            return false
        }
        @SuppressWarnings("unchecked") val message: Map<String?, Object?>? = choice.get("message") as Map<String?, Object?>?
        val finishReason = choice.get("finish_reason") as String?
        if ("content_filter".equals(finishReason)) {
            return false
        }
        if (message == null || message.isEmpty()) {
            return false
        }
        val content: Object? = message.get("content")
        return content is String
    }

    /**
     * 解密单个choice内容 - 非流式
     */
    fun decryptChoiceContent(key: ByteArray?, nonce: ByteArray?, choice: Map<String?, Object?>) {
        val encryptedContent: String? = com.volcengine.ark.runtime.utils.ResponseDecryptUtil.getEncryptedContentFromChoice(choice)
        if (encryptedContent != null && !encryptedContent.isEmpty()) {
            try {
                val decryptedContent: String? = KeyAgreementUtil.decryptStringWithKey(key, nonce, encryptedContent)
                @SuppressWarnings("unchecked") val message: Map<String?, Object?> = choice.get("message") as Map<String?, Object?>
                message.put("content", decryptedContent)
            } catch (e: Exception) {
                @SuppressWarnings("unchecked") val message: Map<String?, Object?> = choice.get("message") as Map<String?, Object?>
                message.put("content", "")
            }
        }
    }

    /**
     * 从choice获取加密内容 - 非流式
     */
    fun getEncryptedContentFromChoice(choice: Map<String?, Object?>): String? {
        @SuppressWarnings("unchecked") val message: Map<String?, Object?>? = choice.get("message") as Map<String?, Object?>?
        if (message == null) {
            return null
        }
        val contentObj: Object? = message.get("content")
        return if (contentObj is String) contentObj as String? else null
    }
}