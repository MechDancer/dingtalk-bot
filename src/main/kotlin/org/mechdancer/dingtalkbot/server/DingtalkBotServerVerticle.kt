package org.mechdancer.dingtalkbot.server

import io.vertx.core.AbstractVerticle
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.Router
import java.net.InetAddress

class DingtalkBotServerVerticle : AbstractVerticle() {
    override fun start() {
        val httpServer = vertx.createHttpServer()

        val router = Router.router(vertx)
        println(InetAddress.getLocalHost().hostAddress)
        router.route("/send/:bot")
            .method(HttpMethod.POST)
            .handler {
                println(it.pathParam("bot"))
                it.request().bodyHandler { buffer ->
                    println(buffer.toString())
                }
                println(it.request().headers().entries().joinToString("\n"))
                it.response().end()
            }
        httpServer.requestHandler(router)
        httpServer.listen()
    }
}