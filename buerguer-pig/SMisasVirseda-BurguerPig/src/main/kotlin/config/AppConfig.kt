package config

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

object AppConfig {
    lateinit var appData: String
    lateinit var textoPath: String
    lateinit var csvPath: String
    lateinit var binPath: String
    lateinit var serializablePath: String
    lateinit var xmlPath: String
    lateinit var jsonPath: String

    init {
        loadProperties()
        initStorage()
    }

    fun loadProperties() {
        val properties = Properties()
        properties.load(AppConfig::class.java.getResourceAsStream("/config.properties"))
        appData = properties.getProperty("app.data.dir")
        textoPath = appData + File.separator + properties.getProperty("texto.dir")
        csvPath = appData + File.separator + properties.getProperty("csv.dir")
        binPath = appData + File.separator + properties.getProperty("bin.dir")
        serializablePath = appData + File.separator + properties.getProperty("serializable.dir")
        xmlPath = appData + File.separator + properties.getProperty("xml.dir")
        jsonPath = appData + File.separator + properties.getProperty("json.dir")
    }

    fun initStorage() {
        Files.createDirectories(Paths.get(textoPath))
        Files.createDirectories(Paths.get(csvPath))
        Files.createDirectories(Paths.get(binPath))
        Files.createDirectories(Paths.get(serializablePath))
        Files.createDirectories(Paths.get(xmlPath))
        Files.createDirectories(Paths.get(jsonPath))
    }
}