package dev.xyzjesper.xcolor.config

import java.io.File

object ConfigManager {

    private val settingsFile = File("plugins/XColor/config.json")
    private val playerFile = File("plugins/XColor/data/playerData.json")

    val settings = settingsFile.loadConfig(SettingsData(
        messages = Messages()
    ))
    val playerData = playerFile.loadConfig(PlayerData())

    fun save() {
        settingsFile.writeText(json.encodeToString(settings))
        playerFile.writeText(json.encodeToString(playerData))
    }

}