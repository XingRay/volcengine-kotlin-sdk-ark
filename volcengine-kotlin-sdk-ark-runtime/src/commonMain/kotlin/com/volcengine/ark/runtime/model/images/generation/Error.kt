package com.volcengine.ark.runtime.model.images.generation

import kotlinx.serialization.Serializable

@Serializable
data class Error(
    /**
     * The reason for failed image generation.
     */
    val message: String? = null,

    /**
     * The error code for failed image generation.
     */
    val code: String? = null
)