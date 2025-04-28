package com.github.clientsiderz.command.impl

import com.github.clientsiderz.command.SlashCommand
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData

class EchoCommand : SlashCommand("echo", "Replies with the text provided") {
    override fun execute(event: SlashCommandInteractionEvent) {
        val text = event.getOption("text")?.asString ?: "No text provided."
        event.reply(text).queue()
    }
    override fun addOptions(commandData: SlashCommandData): SlashCommandData {
        return commandData.addOption(OptionType.STRING, "text", "The text to repeat.", true)
    }
}