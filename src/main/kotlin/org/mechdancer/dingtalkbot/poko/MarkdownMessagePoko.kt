package org.mechdancer.dingtalkbot.poko

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mechdancer.dingtalkbot.poko.anno.StringMessage
import org.mechdancer.dingtalkbot.poko.anno.MessageType

@Serializable
data class Markdown(val title: String,
                    @StringMessage(MessageType.MARKDOWN)
                    val text: String)

@Serializable
data class MarkdownMessagePoko(val markdown: Markdown, @Optional val at: At?=null) : Message {
	@SerialName("msgtype")
	override val messageType = "markdown"
}