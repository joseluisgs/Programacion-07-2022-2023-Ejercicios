package config

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

private val LOCAL_PATH = "${System.getProperty("user.dir")}${File.separator}"

object ConfigApp {
    lateinit var APP_DIR: String

    lateinit var PersonaCsv: String
    lateinit var PersonaJson: String
    lateinit var PersonaXml: String


    init {
        loadConfig()
        initstructure()
    }

    private fun initstructure() {
        Files.createDirectories(Paths.get(APP_DIR))

        Files.createDirectories(Paths.get(PersonaCsv))
        Files.createDirectories(Paths.get(PersonaJson))
        Files.createDirectories(Paths.get(PersonaXml))
    }

    private fun loadConfig() {

        val properties = Properties()
        properties.load(ConfigApp::class.java.getResourceAsStream("/config.properties"))

        APP_DIR = properties.getProperty("app.storage.dir") ?: "data"
        APP_DIR = "$LOCAL_PATH$APP_DIR"

        PersonaCsv = "$APP_DIR${File.separator}" + "persona" + File.separator + "csv"
        PersonaJson = "$APP_DIR${File.separator}" + "persona" + File.separator + "json"
        PersonaXml = "$APP_DIR${File.separator}" + "persona" + File.separator + "xml"
    }
}

