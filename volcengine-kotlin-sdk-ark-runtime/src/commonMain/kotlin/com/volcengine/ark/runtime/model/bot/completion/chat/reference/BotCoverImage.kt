package com.volcengine.ark.runtime.model.bot.completion.chat.reference

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class BotCoverImage {
    var url: String? = null

    private var width: Integer? = null

    private var height: Integer? = null

    fun getWidth(): Integer? {
        return width
    }

    fun setWidth(width: Integer?) {
        this.width = width
    }

    fun getHeight(): Integer? {
        return height
    }

    fun setHeight(height: Integer?) {
        this.height = height
    }

    @Override
    fun toString(): String? {
        return "BotCoverImage{" +
                "url='" + url + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}'
    }
}
