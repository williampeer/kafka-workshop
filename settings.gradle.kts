pluginManagement {
    val kotlin_version: String by settings
    plugins {
        id("org.jetbrains.kotlin.plugin.serialization") version kotlin_version
    }
}

rootProject.name = "kafka-workshop"
