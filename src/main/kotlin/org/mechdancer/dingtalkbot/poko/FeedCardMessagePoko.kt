package org.mechdancer.dingtalkbot.poko

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Entry(val title: String,
                 val messageURL: String,
                 var picURL: String)

@Serializable
data class FeedCard(val links: List<Entry>)

@Serializable
data class FeedCardMessagePoko(val feedCard: FeedCard) : Message {
    @SerialName("msgtype")
    override val messageType: String = "feedCard"
}