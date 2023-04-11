package storage.medicion

import config.ConfigApp
import dto.MapaMedicionesDto
import mapper.toMediciones
import mapper.toMedicionesDto
import model.Medicion
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import java.io.File
import java.time.LocalDate

class MedicionStorageServiceXml{

    private val config = ConfigApp
    private val file = File(config.APP_DATA+File.separator+"mediciones.xml")
    private val persister = Persister()
    private val logger = KotlinLogging.logger {  }

    fun saveAll(entities: Map<LocalDate, List<Medicion>>) {
        logger.debug { "Se guardan todas las mediciones sobre el fichero XML." }
        persister.write(entities.toMedicionesDto(), file)
    }

   fun loadAll(): Map<LocalDate, List<Medicion>> {
        logger.debug { "Se cargan todas las mediciones de el fichero XML." }
        return persister.read(MapaMedicionesDto::class.java, file).toMediciones()
    }
}