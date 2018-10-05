package org.mechdancer.dingtalkbot.network

import kotlinx.serialization.json.JSON
import okhttp3.*
import org.mechdancer.dingtalkbot.poko.Message
import java.io.IOException

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