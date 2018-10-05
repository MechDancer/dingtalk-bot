package org.mechdancer.dingtalkbot.builder

import org.mechdancer.dingtalkbot.poko.Entry
import org.mechdancer.dingtalkbot.poko.FeedCard
import org.mechdancer.dingtalkbot.poko.FeedCardMessagePoko

class EntryBuilder {
	var title = ""
	var messageUrl = ""
	var pictureUrl = ""

	fun build() = Entry(title, messageUrl, pictureUrl)
}

class FeedCardMessageBuilder internal constructor() {
	private val entries = mutableListOf<Entry>()

	fun addEntry(block: EntryBuilder.() -> Unit) =
			EntryBuilder().apply(block).build().let(entries::add)

	internal fun build() = FeedCardMessagePoko(FeedCard(entries))
}

fun feedCardMessage(block: FeedCardMessageBuilder.() -> Unit) =
		FeedCardMessageBuilder().apply(block).build()