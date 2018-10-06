package org.mechdancer.dingtalkbot.builder

import org.mechdancer.dingtalkbot.poko.At
import org.mechdancer.dingtalkbot.poko.Text
import org.mechdancer.dingtalkbot.poko.TextMessagePoko

fun textMessage(text: String, at: At? = null) = TextMessagePoko(Text(text), at)

fun textMessage(text: String, atBlock: AtBuilder.() -> Unit) =
		textMessage(text, at(atBlock))
