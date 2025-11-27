package dev.xyzjesper.xcolor.utils

import dev.xyzjesper.xcolor.config.ConfigManager
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

object ParseFormats {


    fun parseChatFormat(e: AsyncChatEvent): Component {
    
        val format = ConfigManager.settings.chatFormat.replace(
            "{playerName}", MiniMessage.miniMessage().serialize(e.player.displayName())
        ).replace(
            "{message}", MiniMessage.miniMessage().serialize(e.message())  
        )

        return MiniMessage.miniMessage().deserialize(format)
    }

    fun parseNameFormat(playerName: String, prefix: String?): Component {
        var format = ConfigManager.settings.nameFormat
        if (!prefix.isNullOrEmpty()) {
            val parsedPrefix = ConfigManager.settings.prefixFormat.replace(
                "{text}", prefix
            )

            format = format.replace(
                "{prefix}", parsedPrefix
            )
        }
        // -----------------------------
        format = format.replace(
            "{name}", playerName
        )

        return MiniMessage.miniMessage().deserialize(format)
    }

}