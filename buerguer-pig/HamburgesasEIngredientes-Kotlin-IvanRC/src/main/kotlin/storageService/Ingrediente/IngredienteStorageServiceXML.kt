package storageService.Ingrediente

import config.ConfigApp
import dto.ingrediente.ListaIngredientesDto
import mapper.toDto
import mapper.toIngrediente
import model.Ingrediente
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import java.io.File

class IngredienteStorageServiceXML: IngredienteStorageService {

    private val config = ConfigApp
    private val file = File(config.APP_DATA+File.separator+"ingrediente.xml")
    private val logger = KotlinLogging.logger {  }

    private val serializer = Persister()

    override fun saveAll(entities: List<Ingrediente>) {
        logger.debug { "Se guardan todos los ingredientes en el archivo XML." }
        serializer.write(entities.toDto(), file)
    }

    override fun loadAll(): List<Ingrediente> {
        logger.debug { "Se cargan todos los ingredientes del archivo XML." }
        if(!file.exists()) return emptyList()
        return serializer.read(dto.ingrediente.ListaIngredientesDto::class.java, file).toIngrediente()
    }
}