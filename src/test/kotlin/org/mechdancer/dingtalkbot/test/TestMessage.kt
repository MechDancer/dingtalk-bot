package org.mechdancer.dingtalkbot.test

import org.junit.Before
import org.junit.Test
import org.mechdancer.dingtalkbot.DingtalkBot
import org.mechdancer.dingtalkbot.builder.*
import org.mechdancer.dingtalkbot.poko.AvatarOption

class TestMessage {

	lateinit var bot: DingtalkBot

	@Before
	fun init() {
		bot = DingtalkBot("")
		bot.onResponse = { _, r ->
			println(r.body()?.string())
		}
	}

	@Test
	fun testMarkdown() {
		val message = markdownMessage {
			title = "我叫你一声你敢答应吗"
			text = """
				![我叫你一声你敢答应吗](https://github.com/MechDancer/mechdancer.github.io/blob/master/css/images/mechdancer2.png?raw=true)


				# 我叫你一声你敢答应吗


				## 我叫你一声你敢答应吗


				### 我叫你一声你敢答应吗


				#### 我叫你一声你敢答应吗


				##### 我叫你一声你敢答应吗


				> 我叫你一声你敢答应吗


				**我叫你一声你敢答应吗**


				*我叫你一声你敢答应吗*


				- 我叫你一声你敢答应吗
				- 我叫你一声你敢答应吗


				1. 我叫你一声你敢答应吗
				2. 我叫你一声你敢答应吗

			""".trimIndent()
		}

		bot.postMessage(message)
	}

	@Test
	fun testSingleActionCard() {
		val message = singleActionCardMessage {
			title = "我叫你一声你敢答应吗"
			text = """
				![我叫你一声你敢答应吗](https://github.com/MechDancer/mechdancer.github.io/blob/master/css/images/mechdancer2.png?raw=true)


				# 我叫你一声你敢答应吗


				## 我叫你一声你敢答应吗


				### 我叫你一声你敢答应吗


				#### 我叫你一声你敢答应吗


				##### 我叫你一声你敢答应吗


				> 我叫你一声你敢答应吗


				**我叫你一声你敢答应吗**


				*我叫你一声你敢答应吗*


				- 我叫你一声你敢答应吗
				- 我叫你一声你敢答应吗


				1. 我叫你一声你敢答应吗
				2. 我叫你一声你敢答应吗

			""".trimIndent()
			singleTitle = "阅读全文"
			singleURL = "http://mechdancer.org"
			avatarOption = AvatarOption.HIDE
		}


		bot.postMessage(message)
	}

	@Test
	fun testFeedCard() {
		val message = feedCardMessage {
			addEntry {
				title = "师大二附交流活动简报"
				messageUrl = "http://mechdancer.org/2018/09/16/briefing/communication-defy-2018.09.16/"
				pictureUrl = "http://mechdancer.org/images/communication-defy-2018.09.16.jpg"
			}
			addEntry {
				title = "规范导航器接口"
				messageUrl = "http://mechdancer.org/2018/09/14/programming/navigation-interface/"
				pictureUrl = "https://github.com/MechDancer/mechdancer.github.io/blob/master/css/images/mechdancer2.png?raw=true"
			}
		}
		bot.postMessage(message)
	}

	@Test
	fun testMultiActionCard() {
		val message = multiActionCardMessage {
			title = "我叫你一声你敢答应吗"
			text = "# 我叫你一声你敢答应吗"
			avatarOption = AvatarOption.HIDE

			repeat(3) {
				addButton {
					title = "我叫你一声你敢答应吗"
					text = "我叫你一声你敢答应吗"
				}
			}
		}
		bot.postMessage(message)
	}

	@Test
	fun testText() {
		val message = textMessage("我叫你一声你敢答应吗")
		bot.postMessage(message)
	}

	@Test
	fun testLink() {
		val message = linkMessage {
			title = "我叫你一声你敢答应吗"
			text = "我叫你一声你敢答应吗"
			picUrl = "https://github.com/MechDancer/mechdancer.github.io/blob/master/css/images/mechdancer2.png?raw=true"
			messageUrl = "http://mechdancer.org"
		}
		bot.postMessage(message)
	}
}