package storage.accidentes

import config.ConfigApp
import dto.conjuntoDeAccidentes.ConjuntoDeAccidenteDto
import mapper.accidente.toAccidente
import mapper.accidente.toAccidentes
import mapper.accidente.toAccidentesDto
import mapper.accidente.toDto
import model.Accidente
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import java.io.File

class AccidentesStorageServiceXML : AccidenteStorageService {

    private val configApp = ConfigApp
    private val file = File(configApp.APP_DATA+File.separator+"accidentes.xml")
    private val logger = KotlinLogging.logger {}

    private val serializer = Persister()

    override fun saveAll(entities: List<Accidente>) {
        logger.debug { "Se guardan todos los accidentes en el fichero XML." }
        serializer.write(ConjuntoDeAccidenteDto(entities.toAccidentesDto()), file)
    }

    override fun loadAll(): List<Accidente> {
        logger.debug { "Se cargan todos los accidentes del fichero XML." }
        return serializer.read(ConjuntoDeAccidenteDto::class.java, file).accidentes.toAccidentes()
    }
}