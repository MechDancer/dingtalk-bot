package org.mechdancer.dingtalkbot.poko.anno

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
annotation class StringMessage(val type: MessageType) {
    enum class MessageType {
        PLAIN,
        MARKDOWN
    }
}

