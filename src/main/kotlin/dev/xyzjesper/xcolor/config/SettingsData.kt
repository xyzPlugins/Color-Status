package dev.xyzjesper.xcolor.config

import kotlinx.serialization.Serializable

@Serializable
data class SettingsData(
    val renameEnabled: Boolean = true,
    val nameFormat: String = "{prefix} {name}",
    val prefixFormat: String = "[{text}]",
    val chatEnabled: Boolean = false,
    val chatFormat: String = "{playerName}: {message}",
    val messages: Messages
)

@Serializable
data class Messages(
    var prefixResetMessage: String = "<color:#94c8ff>You are now back to your old self and no longer have a prefix.</color>",
    var prefixSetMessage: String = "<color:#ffb0de>Your new prefix has been set. And you can now glisten with your new cosmetics</color>",
    var nameResetMessage: String = "<color:#94c8ff>You are now back to your old self and no longer have a custom name.</color>",
    var nameSetMessage: String = "<color:#ffb0de>Your new name has been set. And you can now glisten with your new cosmetics</color>",
)

@Serializable
data class PlayerData(var player: MutableMap<String, PlayerConfig> = mutableMapOf())

@Serializable
data class PlayerConfig(var prefix: String? = null, var name: String? = null)