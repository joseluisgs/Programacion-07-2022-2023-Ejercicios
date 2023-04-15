package config

import mu.KotlinLogging
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

private val logger = KotlinLogging.logger {}

private val LOCAL_PATH = "${System.getProperty("user.dir")}${File.separator}"

object AppConfig {
    private var _APP_DATA: String = "data"
    val APP_DATA: String
        get() = _APP_DATA

    init {
        loadProperties()
        initStorage()
    }

    private fun initStorage() {
        logger.debug { "Creando directorio data si no existe" }
        Files.createDirectories(Paths.get(_APP_DATA))
    }

    private fun loadProperties() {
        logger.debug { "Cargando configuración de la aplicación" }
        val properties = Properties()
        properties.load(AppConfig::class.java.getResourceAsStream("/config.properties"))

        _APP_DATA = LOCAL_PATH + properties.getProperty("app.storage.dir", "data")

        logger.info { "Configuración: app.storage.dir = $_APP_DATA" }
    }
}