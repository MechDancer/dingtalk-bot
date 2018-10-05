package org.mechdancer.dingtalkbot

import kotlinx.io.IOException
import okhttp3.Call
import okhttp3.Response
import org.mechdancer.dingtalkbot.network.HttpClient
import org.mechdancer.dingtalkbot.network.callback
import org.mechdancer.dingtalkbot.poko.Message

class DingtalkBot(val webHook: String) {

	var onFailure = { _: Call, _: IOException -> }
	var onResponse = { _: Call, _: Response -> }

	inline fun <reified T : Message> postMessage(message: T) {
		HttpClient.postMessage(webHook, message, callback(onFailure, onResponse))
	}
}