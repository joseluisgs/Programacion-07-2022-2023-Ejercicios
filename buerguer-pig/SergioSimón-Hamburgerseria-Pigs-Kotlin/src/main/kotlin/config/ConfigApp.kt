package config

import mu.KotlinLogging
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

private val logger = KotlinLogging.logger {}

// Guardamos la ruta de la carpeta en la que trabajaremos
private val LOCAL_PATH = "${System.getProperty("user.dir")}${File.separator}"

object ConfigApp {
    val APP_NAME = "Hamburguesería Pigs"
    val APP_VERSION = "1.0.0"
    lateinit var APP_AUTHOR: String
    lateinit var APP_DATA: String

    lateinit var INGREDIENTE_BINARIO_PATH: String
    lateinit var INGREDIENTE_CSV_PATH: String
    lateinit var INGREDIENTE_JSON_PATH: String
    lateinit var INGREDIENTE_SERIALIZABLE_PATH: String
    lateinit var INGREDIENTE_TEXTO_PATH: String
    lateinit var INGREDIENTE_XML_PATH: String

    lateinit var HAMBURGUESA_BINARIO_PATH: String
    lateinit var HAMBURGUESA_CSV_PATH: String
    lateinit var HAMBURGUESA_JSON_PATH: String
    lateinit var HAMBURGUESA_SERIALIZABLE_PATH: String
    lateinit var HAMBURGUESA_TEXTO_PATH: String
    lateinit var HAMBURGUESA_XML_PATH: String

    init {
        loadConfig()
        initstructure()
    }

    private fun initstructure() {
        logger.info("Creando carpetas/esquema de archivos")

        Files.createDirectories(Paths.get(APP_DATA))

        Files.createDirectories(Paths.get(INGREDIENTE_BINARIO_PATH))
        Files.createDirectories(Paths.get(INGREDIENTE_CSV_PATH))
        Files.createDirectories(Paths.get(INGREDIENTE_JSON_PATH))
        Files.createDirectories(Paths.get(INGREDIENTE_SERIALIZABLE_PATH))
        Files.createDirectories(Paths.get(INGREDIENTE_TEXTO_PATH))
        Files.createDirectories(Paths.get(INGREDIENTE_XML_PATH))

        Files.createDirectories(Paths.get(HAMBURGUESA_BINARIO_PATH))
        Files.createDirectories(Paths.get(HAMBURGUESA_CSV_PATH))
        Files.createDirectories(Paths.get(HAMBURGUESA_JSON_PATH))
        Files.createDirectories(Paths.get(HAMBURGUESA_SERIALIZABLE_PATH))
        Files.createDirectories(Paths.get(HAMBURGUESA_TEXTO_PATH))
        Files.createDirectories(Paths.get(HAMBURGUESA_XML_PATH))
    }

    private fun loadConfig() {
        logger.info("Cargando config")

        // Establece la configuración para el esquema de archivos
        logger.debug { "Estableciendo configuración del esquema de archivos" }
        val properties = Properties()
        // Recuerda crear el archivo config.properties que está en la carpeta resources
        properties.load(ConfigApp::class.java.getResourceAsStream("/config.properties"))

        // Establece el nombre de la carpeta de los archivos
        APP_DATA = properties.getProperty("app.storage.dir") ?: "data"
        // Configura la ruta de la carpeta de trabajo
        APP_DATA = "$LOCAL_PATH$APP_DATA"
        APP_AUTHOR = properties.getProperty("app.author") ?: "SergioSF"

        // Rutas para las carpetas de los archivos
        INGREDIENTE_BINARIO_PATH = "$APP_DATA${File.separator}" + "ingredientes" + File.separator + "binario"
        INGREDIENTE_CSV_PATH = "$APP_DATA${File.separator}" + "ingredientes" + File.separator + "csv"
        INGREDIENTE_JSON_PATH = "$APP_DATA${File.separator}" + "ingredientes" + File.separator + "json"
        INGREDIENTE_SERIALIZABLE_PATH = "$APP_DATA${File.separator}" + "ingredientes" + File.separator + "serializable"
        INGREDIENTE_TEXTO_PATH = "$APP_DATA${File.separator}" + "ingredientes" + File.separator + "text"
        INGREDIENTE_XML_PATH = "$APP_DATA${File.separator}" + "ingredientes" + File.separator + "xml"

        HAMBURGUESA_BINARIO_PATH = "$APP_DATA${File.separator}" + "hamburguesa" + File.separator + "binario"
        HAMBURGUESA_CSV_PATH = "$APP_DATA${File.separator}" + "hamburguesa" + File.separator + "csv"
        HAMBURGUESA_JSON_PATH = "$APP_DATA${File.separator}" + "hamburguesa" + File.separator + "json"
        HAMBURGUESA_SERIALIZABLE_PATH = "$APP_DATA${File.separator}" + "hamburguesa" + File.separator + "serializable"
        HAMBURGUESA_TEXTO_PATH = "$APP_DATA${File.separator}" + "hamburguesa" + File.separator + "text"
        HAMBURGUESA_XML_PATH = "$APP_DATA${File.separator}" + "hamburguesa" + File.separator + "xml"
    }
}