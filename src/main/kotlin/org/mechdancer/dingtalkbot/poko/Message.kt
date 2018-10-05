package org.mechdancer.dingtalkbot.poko

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
interface Message {
	@SerialName("msgtype")
	val messageType: String
}