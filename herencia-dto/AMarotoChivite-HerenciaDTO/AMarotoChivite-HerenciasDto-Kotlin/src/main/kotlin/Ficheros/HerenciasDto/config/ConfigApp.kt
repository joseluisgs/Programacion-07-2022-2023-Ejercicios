package Ficheros.HerenciasDto.config

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

object ConfigApp {
    var APP_DATA: String = ""

    private val LOCAL_PATH =
        "${System.getProperty("user.dir")}${File.separator}src${File.separator}main${File.separator}kotlin${File.separator}Ficheros${File.separator}HerenciasDTO${File.separator}"

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