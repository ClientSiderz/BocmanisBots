package com.github.clientsiderz.command.impl

import com.github.clientsiderz.command.SlashCommand
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

class PingCommand : SlashCommand("ping", "Replies with pong") {
    override fun execute(event: SlashCommandInteractionEvent) {
        event.reply("Pong!").queue()
    }
}