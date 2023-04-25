package services.storage.personas


import config.ConfigApp
import dto.PersonasDto
import mappers.toDto
import mappers.toPersonas
import models.Persona
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import services.storage.base.StorageService
import java.io.File

private val logger = KotlinLogging.logger {  }

object PersonasXmlService: StorageService<Persona> {

    private val path = "${ConfigApp.APP_DATA}${File.separator}personas.xml"
    private val serializer = Persister()

    override fun saveAll(items: List<Persona>) {
        logger.debug { "Guardando XML" }

        val file = File(path)
        serializer.write(PersonasDto(items.map { it.toDto() }),file)

    }

override fun loadAll(): List<Persona> {

     logger.debug { "Cargando XML" }

     val file = File(path)
     return serializer.read(PersonasDto::class.java,file).toPersonas()
 }
}