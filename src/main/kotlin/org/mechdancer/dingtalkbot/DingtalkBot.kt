package org.mechdancer.dingtalkbot

import org.mechdancer.dingtalkbot.network.HttpClient
import org.mechdancer.dingtalkbot.poko.Message
import org.mechdancer.dingtalkbot.server.DingtalkResult
import java.net.URLEncoder
import java.nio.charset.Charset
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


class DingtalkBot(val webHook: String, val secret: String? = null) {

    var onFailed = { _: Throwable -> }
    var onSuccess = { _: DingtalkResult -> }

    inline fun <reified T : Message> postMessageAsync(message: T) =
        HttpClient.postMessageAsync(encrypt(), message, onFailed, onSuccess)


    suspend inline fun <reified T : Message> postMessage(message: T) =
        runCatching { HttpClient.postMessage(encrypt(), message) }
            .onSuccess(onSuccess)
            .onFailure(onFailed)
            .getOrNull()

    inline fun <reified T : Message> postMessageBlocking(message: T) =
        runCatching { HttpClient.postMessageBlocking(encrypt(), message) }
            .onSuccess(onSuccess)
            .onFailure(onFailed)
            .getOrNull()

    private fun sign(timestamp: Long): String {
        require(secret != null)
        val raw = "$timestamp\n$secret"
        fun String.toUTF8Array() = toByteArray(Charset.forName("UTF-8"))
        val mac: Mac = Mac.getInstance("HmacSHA256")
        mac.init(SecretKeySpec(secret.toUTF8Array(), "HmacSHA256"))
        val r = mac.doFinal(raw.toUTF8Array())
        return URLEncoder.encode(String(Base64.getEncoder().encode(r)), "UTF-8")
    }

    @PublishedApi
    internal fun encrypt(timestamp: Long? = null): String {
        if (secret == null) return webHook
        val t = timestamp ?: System.currentTimeMillis()
        val s = sign(t)
        return "$webHook&timestamp=$t&sign=$s"
    }
}


