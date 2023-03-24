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
    }

    private fun initStorage() {
        Files.createDirectories(Paths.get(SERIALIZABLE_PATH))
        Files.createDirectories(Paths.get(CSV_PATH))
    }
}
