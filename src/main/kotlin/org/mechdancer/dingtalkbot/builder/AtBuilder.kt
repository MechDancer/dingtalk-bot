package org.mechdancer.dingtalkbot.builder

import org.mechdancer.dingtalkbot.poko.At

class AtBuilder internal constructor() {
    var atMobiles = listOf<String>()
    var atAll = false

    internal fun build() = At(atMobiles, atAll)
}

fun at(block: AtBuilder.() -> Unit) = AtBuilder().apply(block).build()
