package org.mechdancer.dingtalkbot.builder

import org.mechdancer.dingtalkbot.poko.*


sealed class ActionCardBuilder {
	var title = ""
	var text = ""

	var buttonOrientation: ButtonOrientation? = null
	var avatarOption: AvatarOption? = null

	internal abstract val buttons: List<Button>


	class SingleActionCardBuilder internal constructor() : ActionCardBuilder() {
		override val buttons = listOf<Button>()

		var singleTitle = ""
		var singleURL = ""
	}

	class MultiActionCardBuilder internal constructor() : ActionCardBuilder() {

		class ButtonBuilder {
			var title: String = ""
			var actionURL: String = ""
		}

		override val buttons = mutableListOf<Button>()

		fun addButton(block: ButtonBuilder.() -> Unit) =
				with(ButtonBuilder().apply(block)) { Button(title, actionURL) }.let(buttons::add)


	}

	internal fun build() = ActionCardMessagePoko(when (this) {
		is SingleActionCardBuilder -> ActionCard(title, text,
				singleTitle, singleURL, null, buttonOrientation, avatarOption)
		is MultiActionCardBuilder  -> ActionCard(title, text,
				null, null, buttons, buttonOrientation, avatarOption)
	})

}


fun singleActionCardMessage(block: ActionCardBuilder.SingleActionCardBuilder.() -> Unit) =
		ActionCardBuilder.SingleActionCardBuilder().apply(block).build()

fun multiActionCardMessage(block: ActionCardBuilder.MultiActionCardBuilder.() -> Unit) =
		ActionCardBuilder.MultiActionCardBuilder().apply(block).build()