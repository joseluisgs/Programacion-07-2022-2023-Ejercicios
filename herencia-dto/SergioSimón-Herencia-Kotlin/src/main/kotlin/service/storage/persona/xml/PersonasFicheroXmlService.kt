package service.storage.persona.xml

import config.ConfigApp
import mappers.personaDto
import mappers.toPersonaList
import models.Persona
import org.simpleframework.xml.core.Persister
import service.storage.persona.PersonaStorageService
import java.io.File

object PersonasFicheroXmlService: PersonaStorageService {

    private val localFile =
        "${ConfigApp.PersonaXml}${File.separator}" + "personas.xml"

    private val serializer = Persister()

    override fun saveAll(items: List<Persona>) {
        val file = File(localFile)
        serializer.write(items.personaDto(), file)
    }

    override fun loadAll(): List<Persona> {
        val file = File(localFile)
        return serializer.read(dto.ListaPersonaDto::class.java, file).toPersonaList() as List<Persona>
    }
}