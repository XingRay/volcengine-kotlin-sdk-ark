package com.volcengine.ark.runtime.model.responses.event
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ItemEvent(type: String?) : OutputEvent(type) {
    @SerialName("item_id")
    var itemId: String? = null
}
