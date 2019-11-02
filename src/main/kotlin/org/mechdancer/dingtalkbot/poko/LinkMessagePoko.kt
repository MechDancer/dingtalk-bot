package org.mechdancer.dingtalkbot.poko

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mechdancer.dingtalkbot.poko.anno.StringMessage

@Serializable
data class Link(@StringMessage(StringMessage.MessageType.PLAIN)
                val text: String,
                val title: String,
                val messageUrl: String,
                val picUrl: String = "")

@Serializable
data class LinkMessagePoko(val link: Link) : Message {
    @SerialName("msgtype")
    override val messageType = "link"
}