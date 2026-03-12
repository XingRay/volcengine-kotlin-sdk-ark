package com.volcengine.ark.runtime.model.responses.tool.doubaoapp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class DoubaoAppFeatureItem {
    @SerialName("type")
    var type: String? = null

    @SerialName("role_description")
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


    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.doubaoapp.DoubaoAppFeatureItem.Builder()
        }
    }
}
