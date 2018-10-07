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
		bot = DingtalkBot("https://oapi.dingtalk.com/robot/send?access_token=fa3502e0d69d7c989e37f712cdc95d371d1f94e3cea9965c7529e1749863745d")
		bot.onSucceed = {
			println(it.bodyAsString())
		}
	}


	@Test
	fun testMarkdown() {
		val message = markdownMessage {
			title = "Hello, world"
			text = "# Hello, World"
			at = at {
				atMobiles = listOf("+86-13800138000", "+86-12345678987")
				atAll = true
			}
		}

		bot.postMessageBlocking(message)
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


		bot.postMessageBlocking(message)
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
			addEntry {
				title="状态机——(1)"
				messageUrl="http://mechdancer.org/2018/09/12/programming/statemachine-2/"
				pictureUrl="http://mechdancer.org/images/statemachine-2-class_diagram.png"
			}
			addEntry {
				title="FTC 2019赛季任务分析及建议 高挂部分"
				messageUrl="http://mechdancer.org/2018/09/09/mechanical/season-analysis-2019-high-hanging/"
				pictureUrl="http://mechdancer.org/images/season-logo.jpg"
			}
		}
		bot.postMessageBlocking(message)
	}

	@Test
	fun testMultiActionCard() {
		val message = multiActionCardMessage {
			title = "我叫你一声你敢答应吗"
			text = "**我叫你一声你敢答应吗**"
			avatarOption = AvatarOption.HIDE

			repeat(3) {
				addButton {
					title = "我叫你一声你敢答应吗$it"
					actionURL = "https://github.com/MechDancer"
				}
			}
		}
		bot.postMessageBlocking(message)
	}

	@Test
	fun testText() {
		val at = at {
			atMobiles = listOf("+86-13800138000", "+86-12345678987")
			atAll = true
		}

		val message = textMessage("Hello, world.", at)

		bot.postMessageBlocking(message)
	}

	@Test
	fun testLink() {
		val message = linkMessage {
			title = "Welcome to MechDancer"
			text = "MechDancer is a robot competition team."
			pictureUrl = "https://github.com/MechDancer/mechdancer.github.io/blob/master/css/images/mechdancer2.png?raw=true"
			messageUrl = "http://mechdancer.org"
		}

		bot.postMessageBlocking(message)
	}


}