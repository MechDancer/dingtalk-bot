package org.mechdancer.dingtalkbot.builder

import org.mechdancer.dingtalkbot.poko.At
import org.mechdancer.dingtalkbot.poko.Markdown
import org.mechdancer.dingtalkbot.poko.MarkdownMessagePoko

class MarkdownMessageBuilder internal constructor() {
    var title = ""
    var text = ""
    var at: At? = null
}

fun markdownMessage(block: MarkdownMessageBuilder.() -> Unit) =
    with(MarkdownMessageBuilder().apply(block)) {
        MarkdownMessagePoko(Markdown(title, text), at)
    }