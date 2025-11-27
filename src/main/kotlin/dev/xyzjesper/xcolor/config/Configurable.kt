package dev.xyzjesper.xcolor.config

interface Configurable {
    fun save()
    fun load() {}
    fun reset() {}
}