package service.storage.persona

import config.AppConfig
import dto.PersonaDto
import mappers.toClass
import models.Persona
import mu.KotlinLogging
import java.io.BufferedInputStream
import java.io.File

private val logger = KotlinLogging.logger{ }

object PersonaFileBinario: PersonaStorageService {
    private val localFile = "${AppConfig.APP_DATA}${File.separator}persona.bin"

    override fun saveAll(elements: List<Persona>): List<Persona> {
        logger.debug { "PersonaFileBinario ->\tsaveAll" }
        val file = File(localFile)
        if (file.exists() && !file.canWrite()) return emptyList()
        file.outputStream().buffered().use {
            elements.forEach{element ->
                it.write(
                    (element.toCsvRow() + ",").toByteArray()
                )
            }
        }
        return elements
    }

    override fun loadAll(): List<Persona> {
        logger.debug { "PersonaFileBinario ->\tloadAll" }
        val file = File(localFile)
        if (!file.exists() || !file.canRead()) return emptyList()
        val personas = mutableListOf<Persona>()
        file.inputStream().buffered().use {
            while (it.available() > 0){
                val id = readCampoBinario(it)
                val nombre = readCampoBinario(it)
                val tipo = readCampoBinario(it)
                val edad = readCampoBinario(it)
                val modulo = readCampoBinario(it)
                personas.add(
                    PersonaDto(id, nombre, tipo, edad, modulo).toClass()
                )
            }
        }
        return personas.toList()
    }

    private fun readCampoBinario(it: BufferedInputStream, separator:Char = ','): String {
        val builder = StringBuilder()
        var char = it.read().toChar()
        while (char != separator){
            builder.append(char)
            char = it.read().toChar()
        }
        return builder.toString()
    }
}