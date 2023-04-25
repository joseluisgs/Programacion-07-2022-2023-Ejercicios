package services.storage.personas

import config.ConfigApp
import mappers.toDto
import models.Alumno
import models.Persona
import models.Profesor
import mu.KotlinLogging
import services.storage.base.StorageService
import java.io.File

private val logger = KotlinLogging.logger {  }
object PersonasCsvService: StorageService<Persona> {

    private val path = "${ConfigApp.APP_DATA}${File.separator}personas.csv"


    override fun saveAll(items: List<Persona>) {

        logger.debug { "Guardando CSV" }

        val file = File(path)

        file.writeText("nombre,tipo,modulo,edad" + "\n")
        items.map { it.toDto() }.forEach {
            file.appendText("${it.nombre},${it.tipo},${it.modulo},${it.edad}"+"\n")
        }

    }

    override fun loadAll(): List<Persona> {
        val file = File(path)

        logger.debug { "Cargando CSV" }
        val personas: MutableList<Persona> = mutableListOf()
       file.readLines()
            .drop(1)
            .map { it.split(",") }
            .map { columnas -> columnas.map { it.trim() } }
            .map { columna -> if(columna[1]=="Profesor"){
                personas.add(Profesor(columna[0],columna[2]))
            }else{ personas.add(Alumno(columna[0],columna[3].toInt()))}
            }
        return personas
    }
}
