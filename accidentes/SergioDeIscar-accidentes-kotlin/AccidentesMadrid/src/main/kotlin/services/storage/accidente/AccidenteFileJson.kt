package services.storage.accidente

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.AppConfig
import dto.AccidenteDto
import models.Accidente
import mu.KotlinLogging
import java.io.File

private val logger = KotlinLogging.logger {}

@ExperimentalStdlibApi
object AccidenteFileJson: AccidenteStorageService {

    private val localFile = "${AppConfig.APP_DATA}${File.separator}accidente.json"

    override fun saveAll(elements: List<Accidente>): List<Accidente> {
        logger.debug { "AccidenteFileJson ->\tsaveAll" }

        val file = File(localFile)
        //if (!canWrite(file)) return emptyList()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter = moshi.adapter<List<AccidenteDto>>()
        val dto = elements.map { it.toDto() }
        val json = jsonAdapter.indent("\t").toJson(dto)

        file.writeText(json)

        return elements
    }

    override fun loadAll(): List<Accidente> {
        logger.debug { "AccidenteFileJson ->\tloadAll" }

        val file = File(localFile)
        //if (!canReed(file)) return emptyList()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter = moshi.adapter<List<AccidenteDto>>()
        val json = file.readText()

        return jsonAdapter.fromJson(json)?.map { it.toClass() } ?: emptyList()
    }
}

private fun canReed(file: File): Boolean{
    return file.exists() && file.canRead()
}

private fun canWrite(file: File): Boolean{
    return !file.exists() || (file.exists() && file.canWrite())
}