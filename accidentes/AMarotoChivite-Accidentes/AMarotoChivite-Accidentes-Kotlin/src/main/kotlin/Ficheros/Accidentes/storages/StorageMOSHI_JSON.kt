package Ficheros.Accidentes.storages

import Ficheros.Accidentes.config.ConfigApp
import Ficheros.Accidentes.models.dto.AccidenteDTO
import Ficheros.Accidentes.utils.readDataOfCSV
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import mu.KotlinLogging
import java.io.File

@ExperimentalStdlibApi
class StorageMOSHI_JSON : IStorageToImportExport<AccidenteDTO> {

    private val logger = KotlinLogging.logger {}

    val localFile = "${ConfigApp.APP_DATA}${File.separator}AccidentesMOSHI_JSON.json"
    val file = File(localFile)

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val jsonAdapter = moshi.adapter<List<AccidenteDTO>>()

    override fun saveInFileWithFilter() {
        logger.debug { "Storage: Escribiendo en JSON con MOSHI" }
        
        val listToExport = readDataOfCSV()

        file.writeText(jsonAdapter.indent("  ").toJson(listToExport))
    }

    override fun readAllModelsInFile(): List<AccidenteDTO> {
        logger.debug { "Storage: Leyendo desde JSON con MOSHI" }

        return jsonAdapter.fromJson(file.readText()) ?: emptyList()
    }
}