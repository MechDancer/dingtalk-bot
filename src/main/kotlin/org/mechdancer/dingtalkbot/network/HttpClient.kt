package org.mechdancer.dingtalkbot.network

import kotlinx.serialization.json.JSON
import okhttp3.*
import org.mechdancer.dingtalkbot.poko.Message
import java.io.IOException
import kotlin.coroutines.experimental.suspendCoroutine

val okHttpClient: OkHttpClient = OkHttpClient.Builder()
		.retryOnConnectionFailure(true)
		.build()

object HttpClient {
	inline fun <reified T : Message> postMessage(url: String, body: T, callback: Callback) {
		Request.Builder()
				.url(url)
				.post(RequestBody.create(
						MediaType.get("application/json"),
						JSON.stringify(body)
				))
				.build()
				.let(okHttpClient::newCall)
				.enqueue(callback)
	}

	suspend inline fun <reified T : Message> postMessage(url: String, body: T) =
			suspendCoroutine<Pair<Call, Response>> {
				postMessage(url, body,
						callback({ _, ioException -> it.resumeWithException(ioException) }
						) { c, r -> it.resume(c to r) })
			}

}

fun callback(onFailure: (Call, IOException) -> Unit = { _, e -> e.printStackTrace() }
             , onResponse: (Call, Response) -> Unit) = object : Callback {
	override fun onFailure(call: Call, e: IOException) {
		onFailure(call, e)
	}

	override fun onResponse(call: Call, response: Response) {
		onResponse(call, response)
	}
}