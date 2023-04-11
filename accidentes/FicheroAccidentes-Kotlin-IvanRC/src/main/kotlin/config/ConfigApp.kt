package config

import mu.KotlinLogging
import java.io.File
import java.nio.file.Files
import java.util.Properties

object ConfigApp {
    lateinit var APP_NAME: String
    lateinit var APP_VERSION : String
    lateinit var APP_AUTHOR: String
    lateinit var APP_DATA: String
    private val LOCAL_PATH: String = System.getProperty("user.dir")+File.separator

    private val logger = KotlinLogging.logger {  }

    init{
        loadProperties()
        initStorage()
    }

    private fun initStorage() {
        logger.debug { "Se inicia el storage" }
        val file = File(APP_DATA)
        if(!file.exists()){
            Files.createDirectory(file.toPath())
        }
    }

    private fun loadProperties() {
        logger.debug { "Se cargan las propiedades" }
        val property = Properties()
        property.load(ConfigApp::class.java.getResourceAsStream("/config.properties"))
        APP_NAME = property.getProperty("APP_NAME") ?: "FicheroAccidentes-Kotlin"
        APP_VERSION = property.getProperty("APP_VERSION") ?: "1.0.0"
        APP_AUTHOR = property.getProperty("APP_AUTHOR") ?: "IvanRoncoCebadera"
        APP_DATA = property.getProperty("APP_DATA") ?: "data"
        APP_DATA = LOCAL_PATH+ APP_DATA
    }

}