package config

import mu.KotlinLogging
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

private val logger = KotlinLogging.logger {}

private val LOCAL_PATH = "${System.getProperty("user.dir")}${File.separator}"
private val LOCAL_PATH_RESOURCES = "$LOCAL_PATH${File.separator}src${File.separator}main${File.separator}resources${File.separator}"

object AppConfig {
    private var _APP_DATA: String = "data"
    val APP_DATA: String get() = _APP_DATA

    private var _APP_DB_URL: String = "jdbc:sqlite:Persona.db"
    val APP_DB_URL: String get() = _APP_DB_URL

    private var _APP_DB_RESET: Boolean = false
    val APP_DB_RESET: Boolean get() = _APP_DB_RESET

    private var _APP_DB_INIT_PATH: String = "init.sql"
    val APP_DB_INIT_PATH: String get() = _APP_DB_INIT_PATH

    private var _APP_DB_RESET_PATH: String = "reset.sql"
    val APP_DB_RESET_PATH: String get() = _APP_DB_RESET_PATH

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

        _APP_DB_URL = properties.getProperty("app.db.url","jdbc:sqlite:Persona.db")
        _APP_DATA = LOCAL_PATH + properties.getProperty("app.storage.dir", "data")
        _APP_DB_RESET = properties.getProperty("app.db.reset", "false").toBoolean()
        _APP_DB_INIT_PATH = LOCAL_PATH_RESOURCES + properties.getProperty("app.db.init.path", "init.sql")
        _APP_DB_RESET_PATH = LOCAL_PATH_RESOURCES + properties.getProperty("app.db.reset.path", "reset.sql")

        logger.info { "Configuración: app.db.url = $APP_DB_URL" }
        logger.info { "Configuración: app.storage.dir = $APP_DATA" }
    }
}