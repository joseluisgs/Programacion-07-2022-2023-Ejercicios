package storage.persona

import config.ConfigApp
import dto.ListaPersonasDto
import mapper.toPersonas
import mapper.toPersonasDto
import models.Persona
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import java.io.File

class PersonaStorageServiceXml: PersonaStorageService {

    private val configApp = ConfigApp
    private val file = File(configApp.APP_DATA+ File.separator+"personas.xml")
    private val persister = Persister()

    private val logger = KotlinLogging.logger {  }

    override fun saveAll(entities: List<Persona>) {
        logger.debug { "Se guardan todas las personas en el fichero XML" }
        persister.write(entities.toPersonasDto(), file)
    }

    override fun loadAll(): List<Persona> {
        logger.debug { "Se cargan todas las personas del fichero XML" }
        if(!file.exists()) return emptyList()
        return persister.read(ListaPersonasDto::class.java, file).toPersonas()
    }
}