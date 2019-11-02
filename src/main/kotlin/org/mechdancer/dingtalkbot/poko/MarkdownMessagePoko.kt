package org.mechdancer.dingtalkbot.poko

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mechdancer.dingtalkbot.poko.anno.StringMessage

@Serializable
data class Markdown(val title: String,
                    @StringMessage(StringMessage.MessageType.MARKDOWN)
                    val text: String)

@Serializable
data class MarkdownMessagePoko(val markdown: Markdown, val at: At? = null) : Message {
    @SerialName("msgtype")
    override val messageType = "markdown"
}