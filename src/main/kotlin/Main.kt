package com.github.clientsiderz

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent

class Main : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.author.isBot) return

        val message = event.message.contentRaw
        if (message.equals("!ping", ignoreCase = true)) {
            event.channel.sendMessage("Pong!").queue()
        }
    }
}

fun main() {
    val token = System.getenv("DISCORD_BOT_TOKEN")
    val jda = JDABuilder.createDefault(token)
        .setActivity(Activity.playing("Currently running."))
        .enableIntents(GatewayIntent.MESSAGE_CONTENT)
        .addEventListeners(Main())
        .build()
}