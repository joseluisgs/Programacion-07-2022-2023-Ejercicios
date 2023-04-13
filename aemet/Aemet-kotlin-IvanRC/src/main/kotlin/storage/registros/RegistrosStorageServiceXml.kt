package storage.registros

import config.ConfigApp
import dto.InformesDto
import mapper.toDto
import mapper.toRegistros
import model.Registro
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import java.io.File
import java.time.LocalDate

class RegistrosStorageServiceXml {

    private val config = ConfigApp
    private val file = File(config.APP_DATA+File.separator+"registros.xml")
    private val persister = Persister()
    private val logger = KotlinLogging.logger {  }

    fun saveAll(entities: List<Map<LocalDate, Registro>>) {
        logger.debug { "Se guardan todas las mediciones sobre el fichero XML." }
        persister.write(entities.toDto(), file)
    }

    fun loadAll(): List<Map<LocalDate, Registro>> {
        logger.debug { "Se cargan todas las mediciones de el fichero XML." }
        if(!file.exists()) return emptyList()
        return persister.read(InformesDto::class.java, file).toRegistros()
    }
}