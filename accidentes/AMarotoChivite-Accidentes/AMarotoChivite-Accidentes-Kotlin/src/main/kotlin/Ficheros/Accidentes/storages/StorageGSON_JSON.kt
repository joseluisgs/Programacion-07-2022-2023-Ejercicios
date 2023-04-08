package Ficheros.Accidentes.storages

import Ficheros.Accidentes.config.ConfigApp
import Ficheros.Accidentes.models.dto.AccidenteDTO
import Ficheros.Accidentes.utils.readDataOfCSV
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import java.io.File

class StorageGSON_JSON : IStorageToImportExport<AccidenteDTO> {

    private val logger = KotlinLogging.logger {}

    val localFile = "${ConfigApp.APP_DATA}${File.separator}AccidentesGSON_JSON.json"
    val file = File(localFile)

    override fun saveInFileWithFilter() {
        logger.debug { "Storage: Escribiendo en JSON con GSON" }

        val listToExport = readDataOfCSV()

        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonString = gson.toJson(listToExport)
        file.writeText(jsonString)
    }

    override fun readAllModelsInFile(): List<AccidenteDTO> {
        logger.debug { "Storage: Leyendo desde JSON con GSON" }

        val gson = Gson()
        val jsonString = file.readText()
        val type = object : TypeToken<List<AccidenteDTO>>() {}.type
        return gson.fromJson(jsonString, type)
    }
}