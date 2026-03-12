package com.volcengine.ark.runtime.model.responses.tool.doubaoapp

import com.fasterxml.jackson.annotation.JsonProperty

class DoubaoAppFeatureItem {
    @JsonProperty("type")
    var type: String? = null

    @JsonProperty("role_description")
    var roleDescription: String? = null

    class Builder {
        private var type: String? = null
        private var roleDescription: String? = null

        fun type(type: String?): Builder {
            this.type = type
            return this
        }

        fun roleDescription(roleDescription: String?): Builder {
            this.roleDescription = roleDescription
            return this
        }

        fun build(): DoubaoAppFeatureItem {
            val featureItem: DoubaoAppFeatureItem = com.volcengine.ark.runtime.model.responses.tool.doubaoapp.DoubaoAppFeatureItem()
            featureItem.type = type
            featureItem.roleDescription = roleDescription
            return featureItem
        }
    }

    @Override
    fun toString(): String? {
        return "DoubaoAppFeatureItem{" +
                "type='" + type + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                '}'
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.doubaoapp.DoubaoAppFeatureItem.Builder()
        }
    }
}
