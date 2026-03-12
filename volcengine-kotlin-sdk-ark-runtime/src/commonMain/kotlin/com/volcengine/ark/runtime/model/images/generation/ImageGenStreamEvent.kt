package com.volcengine.ark.runtime.model.images.generation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Emitted when streaming image generation events.
 * Contains two situations:
 * - partial image
 * - completed
 */
@Serializable
data class ImageGenStreamEvent(
    /**
     * The type of image generating event.
     */
    val type: String? = null,

    /**
     * The model used to generate the images.
     */
    val model: String? = null,

    /**
     * The URL of the generated image.
     */
    val url: String? = null,

    /**
     * The Base 64 encoded string of the generated image.
     */
    @SerialName("b64_json")
    val b64Json: String? = null,

    /**
     * The size of the generated image.
     */
    val size: String? = null,

    /**
     * The error body, if applicable.
     */
    val error: Error? = null,

    /**
     * The usage information for the generation of images.
     */
    val usage: Usage? = null,

    /**
     * The index of the image.
     */
    @SerialName("image_index")
    val imageIndex: Int = 0,

    /**
     * The Unix timestamp when the image was generated.
     */
    @SerialName("created")
    val created: Long = 0,

    @SerialName("tools")
    val tools: List<ContentGenerationTool>? = null
) {

}

