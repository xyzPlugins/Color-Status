package dev.xyzjesper.xcolor.events

import dev.xyzjesper.xcolor.XColorPlugin
import dev.xyzjesper.xcolor.config.ConfigManager
import dev.xyzjesper.xcolor.config.PlayerConfig
import dev.xyzjesper.xcolor.utils.ParseFormats
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.ParsingException
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

object PlayerJoinEvent : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        if (!ConfigManager.settings.renameEnabled) return
        val player = event.player
        val mm = MiniMessage.miniMessage()

        // Data Init
        if (!ConfigManager.playerData.player.contains(player.uniqueId.toString())) {
            ConfigManager.playerData.player[player.uniqueId.toString()] = PlayerConfig(
                name = player.name, prefix = null
            )
            ConfigManager.save()
        }
        val playerData = ConfigManager.playerData.player[player.uniqueId.toString()]

        player.displayName(mm.deserialize(playerData?.name.toString()))
        player.playerListName(mm.deserialize(playerData?.name.toString()))


        if (playerData?.prefix?.isNotEmpty() == true) {
            try {
                val parsedFormat = ParseFormats.parseNameFormat(
                    player.name, playerData.prefix
                )

                player.displayName(parsedFormat)
                player.playerListName(parsedFormat)

            } catch (e: ParsingException) {
                XColorPlugin.instance.server.logger.warning("Please use the new MiniMessage Format.")
            }
        }
    }
}