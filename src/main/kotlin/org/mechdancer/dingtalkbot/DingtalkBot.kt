package org.mechdancer.dingtalkbot

import io.vertx.core.buffer.Buffer
import io.vertx.ext.web.client.HttpResponse
import org.mechdancer.dingtalkbot.network.HttpClient
import org.mechdancer.dingtalkbot.poko.Message

class DingtalkBot(val webHook: String) {

	var onFailed = { _: Throwable -> }
	var onSucceed = { _: HttpResponse<Buffer> -> }

	inline fun <reified T : Message> postMessageAsync(message: T) {
		HttpClient.postMessageAsync(webHook, message, onFailed, onSucceed)
	}

	suspend inline fun <reified T : Message> postMessage(message: T) =
			try {
				HttpClient.postMessage(webHook, message).also { onSucceed(it) }
			} catch (e: Throwable) {
				onFailed(e)
			}

	inline fun <reified T : Message> postMessageBlocking(message: T) =
			HttpClient.postMessageBlocking(webHook, message)
}


