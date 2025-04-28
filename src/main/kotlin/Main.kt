package com.github.clientsiderz

import com.github.clientsiderz.command.SlashCommandManager
import com.github.clientsiderz.command.impl.EchoCommand
import com.github.clientsiderz.command.impl.PingCommand
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent
import java.util.*

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
    val jda: JDA = JDABuilder.createDefault(token)
        .setActivity(Activity.customStatus("Ēdu skrūves"))
        .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
        .addEventListeners(Main())
        .build()

    jda.awaitReady()

    //Katru 10 sekundžu laikā iestāda serveru skaitu
    val timer = Timer()
    timer.scheduleAtFixedRate(object : TimerTask() {
        override fun run() {
            jda.presence.activity = Activity.customStatus("Ēdu skrūves " + jda.guilds.size + " serveros")
        }
    }, 0, 10000)

    val commandManager = SlashCommandManager(jda)
    jda.addEventListener(commandManager)

    commandManager.registerCommand(PingCommand())
    commandManager.registerCommand(EchoCommand())
}