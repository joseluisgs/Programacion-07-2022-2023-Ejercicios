package Ficheros.BurguerPig.config

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

object ConfigApp {

    private val LOCAL_PATH =
        "${System.getProperty("user.dir")}${File.separator}src${File.separator}main${File.separator}kotlin${File.separator}Ficheros${File.separator}BurguerPig${File.separator}"

    var APP_DATA: String = ""

    fun init() {
        loadProperties()
        initStorage()
    }

    private fun loadProperties() {
        val properties = Properties()
        properties.load(ConfigApp::class.java.getResourceAsStream("/config.properties"))

        APP_DATA = properties.getProperty("app.storage.dir") ?: "data"
        APP_DATA = "$LOCAL_PATH$APP_DATA"
    }

    private fun initStorage() {
        // Crear el directorio data si no existe
        Files.createDirectories(Paths.get(APP_DATA))
    }
}