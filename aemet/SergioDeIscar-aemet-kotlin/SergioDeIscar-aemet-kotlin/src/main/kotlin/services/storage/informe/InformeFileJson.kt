package services.storage.informe

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.AppConfig
import dto.InformeDto
import mappers.toClass
import mappers.toDto
import models.Informe
import mu.KotlinLogging
import java.io.File

private val logger = KotlinLogging.logger {  }

@ExperimentalStdlibApi
object InformeFileJson: InformeStorageService{
    private val localFile = "${AppConfig.APP_DATA}${File.separator}informe.json"
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    override fun saveAll(elements: List<Informe>): List<Informe> {
        logger.debug { "InformeFileJson ->\tsaveAll" }

        val file = File(localFile)
        if (file.exists() && !file.canWrite()) return emptyList()

        val jsonAdapter = moshi.adapter<List<InformeDto>>()
        val json = jsonAdapter.indent("\t").toJson(elements.map { it.toDto() })

        file.writeText(json)

        return elements
    }

    override fun loadAll(): List<Informe> {
        logger.debug { "InformeFileJson ->\tloadAll" }

        val file = File(localFile)
        if (!file.exists() || !file.canRead()) return emptyList()

        val jsonAdapter = moshi.adapter<List<InformeDto>>()

        return jsonAdapter.fromJson(file.readText())?.map { it.toClass() } ?: emptyList()
    }
}