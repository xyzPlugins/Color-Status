package dev.xyzjesper.xcolor

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIPaperConfig
import dev.xyzjesper.xcolor.commands.SetColorCommand
import dev.xyzjesper.xcolor.config.ConfigManager
import dev.xyzjesper.xcolor.events.PlayerChatEvent
import dev.xyzjesper.xcolor.events.PlayerJoinEvent
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin


class XColorPlugin : JavaPlugin() {
    companion object {
        lateinit var instance: XColorPlugin
    }

    init {
        instance = this
    }

    override fun onEnable() {
        CommandAPI.onLoad(CommandAPIPaperConfig(this).silentLogs(true))
        CommandAPI.onEnable();

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {

            dev.xyzjesper.xcolor.hooks.PlaceholderAPI(this).register();
            logger.info("[XColorPlugin] Hooking into PlaceholderAPI");
        }

        server.consoleSender.sendMessage(Component.text("[XColor] Enabled XColor successfully!"))

        // Configs
        val settings = ConfigManager.settings
        val playerdata = ConfigManager.playerData

        // Commands
        SetColorCommand()

        // Events
        server.pluginManager.registerEvents(PlayerJoinEvent, this)
        server.pluginManager.registerEvents(PlayerChatEvent, this)
    }

    override fun onDisable() {
        CommandAPI.onDisable()
        server.consoleSender.sendMessage(Component.text("[XColor] Plugin disabled"))
    }

}


