package dev.xyzjesper.xcolor.commands

import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.greedyStringArgument
import dev.jorel.commandapi.kotlindsl.literalArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.xyzjesper.xcolor.config.ConfigManager
import dev.xyzjesper.xcolor.utils.ParseFormats
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage


class SetColorCommand {

    val mm = MiniMessage.miniMessage()

    val command = commandTree("cosmetics") {
        literalArgument("reset") {
            withPermission("colorstatus.command.cosmetics.reset")
            playerExecutor { player, args ->
                ConfigManager.playerData.player[player.uniqueId.toString()]?.prefix = null
                ConfigManager.save()
                
                player.displayName(mm.deserialize(player.name))
                player.playerListName(mm.deserialize(player.name))

                val parsed: Component = mm.deserialize(ConfigManager.settings.messages.nameResetMessage)
                player.sendMessage(parsed)
            }
        }

        literalArgument("prefix") {
            withPermission("colorstatus.command.cosmetics.prefix")
            greedyStringArgument("prefix", optional = false) {
                playerExecutor { player, arguments ->
                    ConfigManager.playerData.player[player.uniqueId.toString()]?.prefix = arguments[0] as String
                    ConfigManager.playerData.player[player.uniqueId.toString()]?.name = player.name
                    ConfigManager.save()
                    
                    val formatedData = ParseFormats.parseNameFormat(
                        player.name, arguments[0] as String
                    )

                    player.displayName(formatedData)
                    player.playerListName(formatedData)

                   player.sendMessage(mm.deserialize(ConfigManager.settings.messages.prefixSetMessage))
               }
           }
        }
    }
}



