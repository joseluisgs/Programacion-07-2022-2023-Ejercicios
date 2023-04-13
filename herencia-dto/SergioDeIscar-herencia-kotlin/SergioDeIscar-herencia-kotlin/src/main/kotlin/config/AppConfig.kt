package config

import mu.KotlinLogging
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.sql.DriverManager
import java.util.*

private val logger = KotlinLogging.logger {}

private val LOCAL_PATH = "${System.getProperty("user.dir")}${File.separator}"

object AppConfig {
    val APP_NAME = "Herencia DTO"
    val APP_VERSION = "1.0.0"
    lateinit var APP_AUTHOR: String
    lateinit var APP_DATA: String
    lateinit var APP_DB_URL: String

    init {
        loadProperties()
        initStorage()
        initDataBase()
    }

    private fun initDataBase() {
        logger.debug { "Creando base de datos si no existe" }
        val connexion = DriverManager.getConnection(APP_DB_URL)
        connexion.autoCommit = false

        val dbCreateIfNot = connexion.prepareStatement("""DROP TABLE IF EXISTS tPersona""")
        logger.info { "Borrado de la tabla tPersona: " + dbCreateIfNot.executeUpdate() }

        val tablaCreateIfNot = connexion.prepareStatement("""CREATE TABLE IF NOT EXISTS tPersona(nIdPersona INTEGER NOT NULL PRIMARY KEY, cNombre TEXT NOT NULL, cTipo TEXT NOT NULL, nEdad INTEGER NULL, cModulo TEXT NULL)""")
        logger.info { "Creación de la tabla tPersona: " + tablaCreateIfNot.executeUpdate() }

        connexion.commit()
        connexion.autoCommit = true
        connexion.close()
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
        APP_DB_URL = properties.getProperty("app.db.url") ?: "jdbc:sqlite:Persona.db"
        APP_DATA = properties.getProperty("app.storage.dir") ?: "data"
        APP_DATA = "$LOCAL_PATH$APP_DATA"

        logger.info { "Configuración: app.author = $APP_AUTHOR" }
        logger.info { "Configuración: app.storage.dir = $APP_DATA" }
    }
}