package dev.xyzjesper.xcolor.events

import dev.xyzjesper.xcolor.config.ConfigManager
import dev.xyzjesper.xcolor.utils.ParseFormats
import io.papermc.paper.event.player.AsyncChatEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

object PlayerChatEvent : Listener {

    @EventHandler
    fun onChat(e: AsyncChatEvent) {
        if (!ConfigManager.settings.chatEnabled) return
        e.isCancelled = true
        val parsedFormat = ParseFormats.parseChatFormat(e)
        e.player.sendMessage(parsedFormat)
    }
}