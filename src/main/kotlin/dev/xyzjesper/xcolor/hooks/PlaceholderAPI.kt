package dev.xyzjesper.xcolor.hooks

import dev.xyzjesper.xcolor.XColorPlugin
import dev.xyzjesper.xcolor.config.ConfigManager
import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.OfflinePlayer

class PlaceholderAPI(xColorPlugin: XColorPlugin) : PlaceholderExpansion() {
    override fun getIdentifier(): String {
        return "xcolor"
    }

    override fun getAuthor(): String {
        return "xyzjesper"
    }

    override fun getVersion(): String {
        return XColorPlugin.instance.description.version
    }

    override fun onRequest(player: OfflinePlayer?, params: String): String {
        if (params.startsWith("cosmetics_prefix_")) {
            val split = params.split("_")
            
            println(split)
            
            if (split.size >= 3) {
                val type = params.split("cosmetics_prefix_")[1]

                val playerData = ConfigManager.playerData.player[player?.uniqueId.toString()] ?: return " "

                println(type)
                
                return when (type) {
                    "name" -> "${playerData.name}"
                    "text" -> "${playerData.prefix}"
                    else -> " "
                }
            }
        }

        return "null"
    }
}