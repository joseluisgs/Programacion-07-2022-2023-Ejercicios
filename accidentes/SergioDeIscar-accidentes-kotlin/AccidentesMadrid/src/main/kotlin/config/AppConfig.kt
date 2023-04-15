package config

import mu.KotlinLogging
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

private val logger = KotlinLogging.logger {}

private val LOCAL_PATH = "${System.getProperty("user.dir")}${File.separator}"

object AppConfig {
    val APP_NAME = "Accidentes Madrid"
    val APP_VERSION = "1.0.0"
    lateinit var APP_AUTHOR: String
    lateinit var APP_DATA: String

    init {
        loadProperties()
        initStorage()
    }

    private fun initStorage() {
        logger.debug { "Creando directorio data si no existe" }
        Files.createDirectories(Paths.get(APP_DATA))
    }

    private fun loadProperties() {
        logger.debug { "Cargando configuración de la aplicación" }
        val properties = Properties()
        properties.load(AppConfig::class.java.getResourceAsStream("/config.properties"))

        APP_AUTHOR = properties.getProperty("app.auth") ?: "nobody"
        APP_DATA = properties.getProperty("app.storage.dir") ?: "data"
        APP_DATA = "$LOCAL_PATH$APP_DATA"

        logger.info { "Configuración: app.author = $APP_AUTHOR" }
        logger.info { "Configuración: app.storage.dir = $APP_DATA" }
    }
}