package services.storage.dupla

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.AppConfig
import dto.DuplaDto
import models.Dupla
import mu.KotlinLogging
import java.io.File

private val logger = KotlinLogging.logger {  }

@ExperimentalStdlibApi
object DuplaFileJson: DuplaStorageService {

    private val localFile = "${AppConfig.APP_DATA}${File.separator}duplas.json"
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    override fun saveAll(elements: List<Dupla>): List<Dupla> {
        logger.debug { "DuplaFileJson ->\tsaveAll" }

        val file = File(localFile)
        if (file.exists() && !file.canWrite()) return emptyList()

        val jsonAdapter = moshi.adapter<List<DuplaDto>>()
        val dto = elements.map { it.toDto() }
        val json = jsonAdapter.indent("\t").toJson(dto)

        file.writeText(json)

        return elements
    }

    override fun loadAll(): List<Dupla> {
        logger.debug { "DuplaFileJson ->\tloadAll" }

        val file = File(localFile)
        if (!file.exists() || !file.canRead()) return emptyList()

        val jsonAdapter = moshi.adapter<List<DuplaDto>>()
        val json = file.readText()

        return jsonAdapter.fromJson(json)?.map { it.toClass() } ?: emptyList()
    }
}