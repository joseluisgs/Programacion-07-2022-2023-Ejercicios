package config

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Properties

private val LOCAL_PATH = "${System.getProperty("user.dir")}${File.separator}"

object AppConfig {
    lateinit var APP_DATA: String
    lateinit var SERIALIZABLE_PATH: String
    lateinit var CSV_PATH: String
    lateinit var TEXT_PATH: String
    lateinit var BINARY_PATH: String
    lateinit var XML_PATH: String
    lateinit var JSON_PATH: String

    init {
        loadProperties()
        initStorage()
    }

    private fun loadProperties() {
        val properties = Properties()
        properties.load(AppConfig::class.java.getResourceAsStream("/config.properties"))
        APP_DATA = properties.getProperty("app.data.dir")
        SERIALIZABLE_PATH = APP_DATA + File.separator + properties.getProperty("app.data.serializable.dir")
        CSV_PATH = APP_DATA + File.separator + properties.getProperty("app.data.csv.dir")
        TEXT_PATH = APP_DATA + File.separator + properties.getProperty("app.data.text.dir")
        BINARY_PATH = APP_DATA + File.separator + properties.getProperty("app.data.binary.dir")
        XML_PATH = APP_DATA + File.separator + properties.getProperty("app.data.xml.dir")
        JSON_PATH = APP_DATA + File.separator + properties.getProperty("app.data.json.dir")
    }

    private fun initStorage() {
        Files.createDirectories(Paths.get(SERIALIZABLE_PATH))
        Files.createDirectories(Paths.get(CSV_PATH))
        Files.createDirectories(Paths.get(TEXT_PATH))
        Files.createDirectories(Paths.get(BINARY_PATH))
        Files.createDirectories(Paths.get(XML_PATH))
        Files.createDirectories(Paths.get(JSON_PATH))
    }
}
