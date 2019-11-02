package org.mechdancer.dingtalkbot.server

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DingtalkResult(
    @SerialName("errmsg")
    val message: String,
    @SerialName("errcode")
    val code: Int)

@Serializable
data class ServerResult(
    val bot: String,
    val result: DingtalkResult
)