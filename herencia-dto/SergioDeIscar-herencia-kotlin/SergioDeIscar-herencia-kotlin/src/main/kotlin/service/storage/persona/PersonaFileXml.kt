package service.storage.persona

import config.AppConfig
import dto.PersonasDto
import mappers.toClass
import mappers.toDto
import models.Persona
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import java.io.File

private val logger = KotlinLogging.logger {}

object PersonaFileXml: PersonaStorageService{
    private val localFile = "${AppConfig.APP_DATA}${File.separator}personas.xml"

    override fun saveAll(elements: List<Persona>): List<Persona> {
        logger.debug { "PersonaFileXml ->\tsaveAll" }
        val file = File(localFile)
        if (file.exists() && !file.canWrite() ) return emptyList()
        val serializer = Persister()
        serializer.write(PersonasDto(elements.map { it.toDto() }), file)
        return elements
    }

    override fun loadAll(): List<Persona> {
        logger.debug { "PersonaFileXml ->\tloadAll" }
        val file = File(localFile)
        if(!file.exists() || !file.canRead()) return emptyList()
        return Persister().read(PersonasDto::class.java, file).personas.map { it.toClass() }
    }
}