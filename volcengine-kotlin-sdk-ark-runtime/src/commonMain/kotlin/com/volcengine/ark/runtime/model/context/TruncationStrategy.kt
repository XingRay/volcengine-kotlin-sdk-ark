package com.volcengine.ark.runtime.model.context

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TruncationStrategy(
    @SerialName("type")
    val type: String? = null,

    @SerialName("last_history_tokens")
    val lastHistoryTokens: Int? = null,

    @SerialName("rolling_tokens")
    val rollingTokens: Boolean? = null,

    @SerialName("max_window_tokens")
    val maxWindowTokens: Int? = null,

    @SerialName("rolling_window_tokens")
    val rollingWindowTokens: Int? = null
) {
    companion object {

        class Builder {
            private var lastHistoryTokens: Int? = null
            private var type: String? = null
            private var rollingTokens: Boolean? = null
            private var maxWindowTokens: Int? = null
            private var rollingWindowTokens: Int? = null

            fun lastHistoryTokens(LastHistoryTokens: Int?): Builder {
                this.lastHistoryTokens = LastHistoryTokens
                return this
            }

            fun type(Type: String?): Builder {
                this.type = Type
                return this
            }

            fun rollingTokens(RollingTokens: Boolean?): Builder {
                this.rollingTokens = RollingTokens
                return this
            }

            fun maxWindowTokens(MaxWindowTokens: Int?): Builder {
                this.maxWindowTokens = MaxWindowTokens
                return this
            }

            fun rollingWindowTokens(RollingWindowTokens: Int?): Builder {
                this.rollingWindowTokens = RollingWindowTokens
                return this
            }

            fun build(): TruncationStrategy {
                return TruncationStrategy(
                    lastHistoryTokens = lastHistoryTokens,
                    type = type,
                    rollingTokens = rollingTokens,
                    maxWindowTokens = maxWindowTokens,
                    rollingWindowTokens = rollingWindowTokens,
                )
            }
        }

        fun builder(): Builder {
            return Builder()
        }
    }
}
