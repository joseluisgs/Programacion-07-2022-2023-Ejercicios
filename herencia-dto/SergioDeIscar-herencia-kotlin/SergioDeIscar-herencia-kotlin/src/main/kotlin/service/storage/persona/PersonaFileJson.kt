package service.storage.persona

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.AppConfig
import dto.PersonaDto
import mappers.toClass
import mappers.toDto
import models.Persona
import mu.KotlinLogging
import java.io.File

private val logger = KotlinLogging.logger {}

@ExperimentalStdlibApi
object PersonaFileJson: PersonaStorageService {
    private val localFile = "${AppConfig.APP_DATA}${File.separator}persona.json"
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    override fun saveAll(elements: List<Persona>): List<Persona> {
        logger.debug { "PersonaFileJson ->\tsaveAll" }
        val file = File(localFile)
        if (file.exists() && !file.canWrite() ) return emptyList()

        val jsonAdapter = moshi.adapter<List<PersonaDto>>()
        file.writeText(
            jsonAdapter.indent("\t").toJson(
                elements.map { it.toDto() }
            )
        )
        return elements
    }

    override fun loadAll(): List<Persona> {
        logger.debug { "PersonaFileJson ->\tloadAll" }
        val file = File(localFile)
        if(!file.exists() || !file.canRead()) return emptyList()

        val jsonAdapter = moshi.adapter<List<PersonaDto>>()
        return jsonAdapter.fromJson(file.readText())?.map { it.toClass() } ?: emptyList()
    }
}