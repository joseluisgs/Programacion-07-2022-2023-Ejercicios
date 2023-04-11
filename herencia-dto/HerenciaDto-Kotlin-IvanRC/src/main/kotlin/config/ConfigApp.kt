package config

import mu.KotlinLogging
import java.io.File
import java.nio.file.Files
import java.util.Properties

object ConfigApp {
    lateinit var APP_NAME: String;
    lateinit var APP_AUTHOR: String;
    lateinit var APP_DATA: String;
    lateinit var APP_VERSION: String;
    val PATH: String = System.getProperty("user.dir")+ File.separator

    private val logger = KotlinLogging.logger {  }

    init{
        loadProperties()
        initStorage()
    }

    private fun initStorage() {
        logger.debug { "Se crea el directorio de storage, en caso de que no exista." }
        val file = File(APP_DATA)
        if(!file.exists()){
            Files.createDirectory(file.toPath())
        }
        APP_DATA += File.separator
    }

    private fun loadProperties() {
        logger.debug { "Se cargan las propiedades del archivo config.properties." }
        val property = Properties()
        APP_NAME = property.getProperty("APP_NAME") ?: "HerenciaDto-Kotlin"
        APP_AUTHOR = property.getProperty("APP_AUTHOR") ?: "IvanRoncoCebadera"
        APP_VERSION = property.getProperty("APP_VERSION") ?: "1.0.0"
        APP_DATA = property.getProperty("APP_DATA") ?: "data"
        APP_DATA = PATH + APP_DATA
    }
}