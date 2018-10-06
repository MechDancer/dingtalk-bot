package org.mechdancer.dingtalkbot.network

import io.vertx.core.Vertx
import io.vertx.core.buffer.Buffer
import io.vertx.ext.web.client.HttpResponse
import io.vertx.ext.web.client.WebClient
import io.vertx.kotlin.coroutines.awaitResult
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.serialization.json.JSON
import org.mechdancer.dingtalkbot.poko.Message


object HttpClient {

	@PublishedApi
	internal val vertx = Vertx.vertx()

	@PublishedApi
	internal val webClient = WebClient.create(vertx)

	suspend inline fun <reified T : Message> postMessage(url: String, body: T) =
			awaitResult<HttpResponse<Buffer>> {
				webClient.postAbs(url)
						.putHeader("Content-Type", "application/json")
						.sendBuffer(Buffer.buffer(JSON.stringify(body)), it)
			}

	inline fun <reified T : Message> postMessageAsync(url: String,
	                                                  body: T,
	                                                  crossinline onFailed: (Throwable) -> Unit,
	                                                  crossinline onSucceeded: (HttpResponse<Buffer>) -> Unit) =
			launch(vertx.dispatcher()) {
				try {
					onSucceeded(postMessage(url, body))
				} catch (e: Throwable) {
					onFailed(e)
				}
			}

	inline fun <reified T : Message> postMessageBlocking(url: String, body: T) =
			runBlocking(vertx.dispatcher()) { postMessage(url, body) }

	fun close() {
		webClient.close()
		vertx.close()
	}
}