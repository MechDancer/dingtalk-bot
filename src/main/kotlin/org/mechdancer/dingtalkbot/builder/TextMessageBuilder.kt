package org.mechdancer.dingtalkbot.builder

import org.mechdancer.dingtalkbot.poko.Text
import org.mechdancer.dingtalkbot.poko.TextMessagePoko


fun textMessage(text: String, atBlock: AtBuilder.() -> Unit = {}) =
		TextMessagePoko(Text(text), at(atBlock))