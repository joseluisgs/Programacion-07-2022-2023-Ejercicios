package storageService.Hamburger

import config.ConfigApp
import mapper.toDto
import mapper.toHamburgesa
import model.Hamburgesa
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import java.io.File

class HamburgesaStorageServiceXML : HamburgesaStorageService {

    private val config = ConfigApp
    private val file = File(config.APP_DATA+ File.separator+"hamburgesa.xml")
    private val logger = KotlinLogging.logger {  }

    private val serializer = Persister()

    override fun saveAll(entities: List<Hamburgesa>) {
        logger.debug { "Se guardan todos las hamburgesas en el archivo XML." }
        serializer.write(entities.toDto(), file)
    }

    override fun loadAll(): List<Hamburgesa> {
        logger.debug { "Se cargan todos las hamburgesas del archivo XML." }
        if(!file.exists()) return emptyList()
        return serializer.read(dto.hamburgesa.ListaHamburgesasDto::class.java, file).toHamburgesa()
    }
}