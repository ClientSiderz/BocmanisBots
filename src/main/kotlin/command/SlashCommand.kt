package com.github.clientsiderz.command

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import org.tinylog.Logger

abstract class SlashCommand(val name: String, val description: String) {

    abstract fun execute(event: SlashCommandInteractionEvent)

    open fun buildCommandData(): SlashCommandData {
        return Commands.slash(name, description)
    }

    open fun handleException(event: SlashCommandInteractionEvent, e: Exception) {
        event.reply("An error occurred while executing this command.").setEphemeral(true).queue()
        Logger.trace(e)
    }

    open fun addOptions(commandData: SlashCommandData): SlashCommandData {
        return commandData
    }
}