# DingtalkBot API
[![Build Status](https://travis-ci.com/MechDancer/dingtalk-bot.svg?branch=master)](https://travis-ci.com/MechDancer/dingtalk-bot)

Library support for  DSL to build and sending *DingtalkBot* messages.

## Usage
You need a *web hook* to create a `DingtalkBot`:
```kotlin
val bot = DingtalkBot(WEB_HOOK)
```
There are three ways to send messages:
* Async
* Blocking
* Suspend

We use [kotlin coroutine](https://github.com/kotlin/kotlinx.coroutines) to implement them.

## Message Type

### Overview

There are six types of messages:
* Text Message
* Markdown Message
* Link Message
* ActionCard Message
* FeedCard Message

Each of them has different data parameters and patterns in serialization.

### 

### Text Message

`TextMessage` is the simplest type of messages. It only contains a plain text, being sent directly.

Here is the way to build a `TextMessage`:

```kotlin
val at = at { 
	atMobiles = listOf("+86-13800138000","+86-12345678987")
	atAll = true
}

val message = textMessage("Hello, world.", at)
```

`At` is optional, you can build an at by the above way.

| Parameter | Optional | Default Value |
| --------- | -------- | ------------- |
| `text`    | No       | -             |
| `at`      | Yes      | `null`        |

### 

### Markdown Message

`MarkdownMessag` is a kind of advanced `TextMessage`. It has extra parameter —— `title` and its `text` supports markdown syntax.

Here is the way to build a `MarkdownMessage`:

```kotlin
val message = markdownMessage {
	title = "Hello, world"
	text = "# Hello, World"
	at = at{
		atMobiles = listOf("+86-13800138000","+86-12345678987")
		atAll = true
	}
}
```

Similarly,`At` is optional.

| Parameter | Optional | Default Value |
| --------- | -------- | ------------- |
| `title`   | No       | -             |
| `text`    | No       | -             |
| `at`      | Yes      | `null`        |



### Link Message

`LinkMessage` is a kind of advanced `TextMessage` as well. It has `title`,`messageUrl`,`pictureUrl` further. Notice that the type of`text` is *plain*.

Here is the way to build a `LinkMessage`:

```kotlin
val message = linkMessage {
	title = "Welcome to MechDancer"
	text = "MechDancer is a robot competition team."
	picUrl = "https://github.com/MechDancer/mechdancer.github.io/blob/master/css/images/mechdancer2.png?raw=true"
	messageUrl = "http://mechdancer.org"
}
```

`LinkMessage` has a picture in its body, and it's optional.

| Parameter    | Optional | Default Value |
| ------------ | -------- | ------------- |
| `title`      | No       | -             |
| `text`       | No       | -             |
| `messageUrl` | No       | -             |
| `pictureUrl` | Yes      | `""`          |



### ActionCard Message



### FeedCard Message



## Contribution

