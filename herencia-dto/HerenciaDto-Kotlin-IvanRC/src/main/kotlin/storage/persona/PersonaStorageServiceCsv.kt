package storage.persona

import config.ConfigApp
import dto.PersonaDto
import mapper.toPersona
import mapper.toPersonasDto
import models.Persona
import mu.KotlinLogging
import java.io.File

class PersonaStorageServiceCsv: PersonaStorageService {

    private val configApp = ConfigApp
    private val file = File(configApp.APP_DATA+ File.separator+"personas.csv")

    private val logger = KotlinLogging.logger {  }

    override fun saveAll(entities: List<Persona>) {
        logger.debug { "Se guardan todas las personas en el fichero CSV" }
        file.writeText("nombre;edad;modulo;tipo\n")
        entities.toPersonasDto().personasDto.forEach {
            file.appendText(
                "${it.nombre};${it.edad ?: ""};${it.modulo ?: ""};${it.tipo}\n"
            )
        }
    }

    override fun loadAll(): List<Persona> {
        logger.debug { "Se cargan todas las personas del fichero CSV" }
        return file.readLines().drop(1)
            .map { it.split(";") }
            .map { it.map { it.trim() } }
            .map {campos ->
                PersonaDto(
                    nombre = campos[0],
                    edad = if(campos[1] != "") campos[1] else null,
                    modulo = if(campos[2] != "") campos[2] else null,
                    tipo = campos[3]
                )
            }
            .map { it.toPersona() }
    }
}