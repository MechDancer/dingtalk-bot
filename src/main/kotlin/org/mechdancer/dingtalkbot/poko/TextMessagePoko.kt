package org.mechdancer.dingtalkbot.poko

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mechdancer.dingtalkbot.poko.anno.StringMessage

@Serializable
data class Text(
    @StringMessage(StringMessage.MessageType.PLAIN)
    val content: String)

@Serializable
data class At(val atMobiles: List<String>, val isAtAll: Boolean)

@Serializable
data class TextMessagePoko(val text: Text, val at: At? = null) : Message {
    @SerialName("msgtype")
    override val messageType = "text"
}