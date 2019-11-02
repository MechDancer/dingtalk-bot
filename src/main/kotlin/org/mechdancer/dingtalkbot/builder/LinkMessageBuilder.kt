package org.mechdancer.dingtalkbot.builder

import org.mechdancer.dingtalkbot.poko.LinkMessagePoko

fun linkMessage(block: LinkBuilder.() -> Unit) =
    LinkMessagePoko(link(block))