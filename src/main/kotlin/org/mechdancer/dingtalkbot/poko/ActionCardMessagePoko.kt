package org.mechdancer.dingtalkbot.poko

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mechdancer.dingtalkbot.poko.anno.StringMessage

enum class ButtonOrientation(private val code: Byte) {
    VERTICAL(0),
    HORIZONTAL(1);

    override fun toString(): String = code.toString()
}

enum class AvatarOption(private val code: Byte) {
    DISPLAY(0),
    HIDE(1);

    override fun toString(): String = code.toString()
}

@Serializable
data class Button(val title: String, val actionURL: String)


@Serializable
data class ActionCard(val title: String,
                      @StringMessage(StringMessage.MessageType.MARKDOWN)
                      val text: String,
                      val singleTitle: String? = null,
                      val singleURL: String? = null,
                      @SerialName("btns")
                      val buttons: List<Button>? = null,
                      @SerialName("btnOrientation")
                      val buttonOrientation: ButtonOrientation? = null,
                      val hideAvatar: AvatarOption? = null
)

@Serializable
data class ActionCardMessagePoko(val actionCard: ActionCard) : Message {
    @SerialName("msgtype")
    override val messageType: String = "actionCard"
}