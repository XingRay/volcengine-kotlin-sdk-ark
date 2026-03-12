package com.volcengine.ark.runtime.model.responses.event

import com.fasterxml.jackson.annotation.JsonProperty

class ItemEvent(type: String?) : OutputEvent(type) {
    @JsonProperty("item_id")
    var itemId: String? = null
}
