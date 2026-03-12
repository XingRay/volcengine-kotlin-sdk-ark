package com.volcengine.ark.runtime.model.context

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class TruncationStrategy {
    @JsonProperty("type")
    var type: String? = null

    @JsonProperty("last_history_tokens")
    private var lastHistoryTokens: Integer? = null

    @JsonProperty("rolling_tokens")
    var rollingTokens: Boolean? = null

    @JsonProperty("max_window_tokens")
    private var maxWindowTokens: Integer? = null

    @JsonProperty("rolling_window_tokens")
    private var rollingWindowTokens: Integer? = null

    constructor()

    constructor(type: String?, lastHistoryTokens: Integer?) {
        this.type = type
        this.lastHistoryTokens = lastHistoryTokens
    }

    fun getLastHistoryTokens(): Integer? {
        return lastHistoryTokens
    }

    fun setLastHistoryTokens(lastHistoryTokens: Integer?) {
        this.lastHistoryTokens = lastHistoryTokens
    }

    fun getMaxWindowTokens(): Integer? {
        return maxWindowTokens
    }

    fun setMaxWindowTokens(maxWindowTokens: Integer?) {
        this.maxWindowTokens = maxWindowTokens
    }

    fun getRollingWindowTokens(): Integer? {
        return rollingWindowTokens
    }

    fun setRollingWindowTokens(rollingWindowTokens: Integer?) {
        this.rollingWindowTokens = rollingWindowTokens
    }

    @Override
    fun toString(): String? {
        return "TruncationStrategy{" +
                "type='" + type + '\'' +
                ", lastHistoryTokens=" + lastHistoryTokens +
                ", rollingTokens=" + rollingTokens +
                '}'
    }

    class Builder private constructor() {
        private var lastHistoryTokens: Integer? = null
        private var type: String? = null
        private var rollingTokens: Boolean? = null
        private var maxWindowTokens: Integer? = null
        private var rollingWindowTokens: Integer? = null

        fun lastHistoryTokens(LastHistoryTokens: Integer?): Builder {
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

        fun maxWindowTokens(MaxWindowTokens: Integer?): Builder {
            this.maxWindowTokens = MaxWindowTokens
            return this
        }

        fun rollingWindowTokens(RollingWindowTokens: Integer?): Builder {
            this.rollingWindowTokens = RollingWindowTokens
            return this
        }

        fun build(): TruncationStrategy {
            val truncationStrategy: TruncationStrategy = com.volcengine.ark.runtime.model.context.TruncationStrategy()
            truncationStrategy.setLastHistoryTokens(lastHistoryTokens)
            truncationStrategy.type = type
            truncationStrategy.rollingTokens = rollingTokens
            truncationStrategy.setMaxWindowTokens(maxWindowTokens)
            truncationStrategy.setRollingWindowTokens(rollingWindowTokens)
            return truncationStrategy
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.context.TruncationStrategy.Builder()
        }
    }
}
