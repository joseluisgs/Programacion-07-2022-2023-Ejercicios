package service.storage.persona.json

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.ConfigApp
import dto.PersonaDto
import mappers.personaDto
import mappers.toPersona
import models.Persona
import service.storage.persona.PersonaStorageService
import java.io.File

@ExperimentalStdlibApi
object PersonaFicheroJsonService: PersonaStorageService {
    private val localFile =
        "${ConfigApp.PersonaJson}${File.separator}" + "personas.json"

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val jsonAdapterDto = moshi.adapter<List<PersonaDto>>()
    private val jsonAdapterJson = moshi.adapter<List<Persona>>()

    override fun saveAll(items: List<Persona>) {
        val file = File(localFile)
        val listPersonaDto: MutableList<PersonaDto> = mutableListOf()

        items.forEach { listPersonaDto.add(it.personaDto()!!)}

        val json = jsonAdapterDto.indent("  ").toJson(listPersonaDto)
        file.writeText(json)
    }

    override fun loadAll(): List<Persona> {
        val file = File(localFile)
        val listPersonas = mutableListOf<Persona>()
        val readPersona = jsonAdapterDto.fromJson(file.readText()) ?: emptyList()
        readPersona.forEach { listPersonas.add(it.toPersona()!!) }
        return listPersonas
    }
}

