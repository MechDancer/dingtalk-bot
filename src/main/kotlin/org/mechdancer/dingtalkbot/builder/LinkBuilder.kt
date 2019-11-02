package org.mechdancer.dingtalkbot.builder

import org.mechdancer.dingtalkbot.poko.Link

class LinkBuilder internal constructor() {
    var text = ""
    var title = ""
    var messageUrl = ""
    var pictureUrl = ""

    internal fun build() = Link(text, title, messageUrl, pictureUrl)
}

fun link(block: LinkBuilder.() -> Unit) = LinkBuilder().apply(block).build()