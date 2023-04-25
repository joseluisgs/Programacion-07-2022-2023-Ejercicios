package services.storage.personas

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.ConfigApp
import dto.PersonaDto
import functions.toPrettyJson
import mappers.toDto
import mappers.toPersona
import models.Persona
import mu.KotlinLogging
import services.storage.base.StorageService
import java.io.File
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex.Empty


private val logger = KotlinLogging.logger {  }


object PersonasJsonService:StorageService<Persona> {

    private val path = "${ConfigApp.APP_DATA}${File.separator}personas.json"

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @OptIn(ExperimentalStdlibApi::class)
    private val jsonAdapter = moshi.adapter<List<PersonaDto>>()


    override fun saveAll(items: List<Persona>) {
        logger.debug { "Guardando JSON" }

        val file = File(path)
        file.writeText(jsonAdapter.toPrettyJson(items.map { it.toDto() }))
    }

    override fun loadAll(): List<Persona> {
        logger.debug { "Cargando JSON" }

        val file = File(path)
        // return ((jsonAdapter.fromJson(file.readText())?: emptyList()) as List<Persona>)
        val xmlDto =  (jsonAdapter.fromJson(file.readText()))
        return xmlDto?.map { it.toPersona() } ?: emptyList()
    }
}