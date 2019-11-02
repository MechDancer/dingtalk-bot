package org.mechdancer.dingtalkbot.poko

import kotlinx.serialization.SerialName


interface Message {
    @SerialName("msgtype")
    val messageType: String
}