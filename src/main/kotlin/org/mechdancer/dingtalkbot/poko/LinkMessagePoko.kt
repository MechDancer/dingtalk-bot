package org.mechdancer.dingtalkbot.poko

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mechdancer.dingtalkbot.poko.anno.StringMessage
import org.mechdancer.dingtalkbot.poko.anno.MessageType

@Serializable
data class Link(@StringMessage(MessageType.PLAIN)
                val text: String,
                val title: String,
                val messageUrl: String,
                @Optional val picUrl: String = "")

@Serializable
data class LinkMessagePoko(val link: Link) : Message {
	@SerialName("msgtype")
	override val messageType = "link"
}