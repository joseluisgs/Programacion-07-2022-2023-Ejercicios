package service.storage.persona

import config.AppConfig
import models.Alumno
import models.Persona
import models.Profesor
import mu.KotlinLogging
import java.io.File

private val logger = KotlinLogging.logger {  }

object PersonaFileCsv: PersonaStorageService {
    private val localFile = "${AppConfig.APP_DATA}${File.separator}persona.csv"

    override fun saveAll(elements: List<Persona>): List<Persona> {
        logger.debug { "PersonaFileCsv ->\tsaveAll" }
        val file = File(localFile)
        if (file.exists() && !file.canWrite() ) return emptyList()
        file.writeText("id,nombre,tipo,edad,modulo\n")
        elements.forEach {
            file.appendText(
                it.toCsvRow()
            )
        }
        return elements
    }

    override fun loadAll(): List<Persona> {
        logger.debug { "PersonaFileCsv ->\tloadAll" }
        val file = File(localFile)
        if(!file.exists() || !file.canRead()) return emptyList()
        return file.readLines()
            .drop(1)
            .map { it.split(",") }
            .map { row ->
                when(row[2]){
                    "Alumno" -> Alumno(
                        row[0].toInt(),
                        row[1],
                        row[3].toInt(),
                    )
                    "Profesor" -> Profesor(
                        row[0].toInt(),
                        row[1],
                        row[4],
                    )
                    else -> throw Exception("Tipo de persona no reconocido")
                }
            }
    }
}