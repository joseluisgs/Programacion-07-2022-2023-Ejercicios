package config

import config.ConfigApp.APP_AUTHOR
import config.ConfigApp.APP_DATA
import config.ConfigApp.APP_VERSION
import mu.KotlinLogging
import java.io.File
import java.util.Properties

private val logger = KotlinLogging.logger {  }

object ConfigApp {

    val APP_NAME: String = "Herencia DTO Kotlin"
    lateinit var APP_AUTHOR: String
    lateinit var APP_VERSION:String
    lateinit var APP_DATA:String

    init {
        loadProperties()
        initStorage(APP_DATA)
    }
}

fun loadProperties(){
    logger.debug { "Cargando propiedades." }
    val properties = Properties()

    APP_AUTHOR = properties.getProperty("app.author") ?: "Daniel Paz"
    APP_VERSION = properties.getProperty("app.version") ?: "1.0.0"
    APP_DATA = properties.getProperty("app.data")?: "${System.getProperty("user.dir")}${File.separator}data"
}

fun initStorage(APP_DATA: String){
    
    logger.debug { "Creando directorio data,si no existe." }


    val data = File(APP_DATA)

    if (!data.exists()){
        data.mkdir()
    }
}
