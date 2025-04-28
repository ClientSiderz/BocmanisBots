package com.github.clientsiderz.command

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class SlashCommandManager(private val jda: JDA) : ListenerAdapter() {

    private val commands: MutableMap<String, SlashCommand> = mutableMapOf()

    fun registerCommand(command: SlashCommand) {
        commands[command.name] = command
        jda.upsertCommand(command.buildCommandData()).queue()
    }

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        val command = commands[event.name]
        try {
            command?.execute(event)
        } catch (e: Exception) {
            command?.handleException(event, e)
        }
    }
}