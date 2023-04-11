package storageService.Ingrediente

import config.ConfigApp
import model.Ingrediente
import mu.KotlinLogging
import java.io.*

class IngredienteStorageServiceSerializable: IngredienteStorageService {

    private val config = ConfigApp
    private val file = File(config.APP_DATA+ File.separator+"ingredientes.ser")
    private val logger = KotlinLogging.logger {}

    override fun saveAll(entities: List<Ingrediente>) {
        logger.debug { "Se guardan todos los ingredientes en el fichero serializable." }
        ObjectOutputStream(FileOutputStream(file)).use{
            it.writeObject(entities)
        }
    }

    override fun loadAll(): List<Ingrediente> {
        logger.debug { "Se cargán todos los ingredientes del fichero serializable." }
        var ingredientes = mutableListOf<Ingrediente>()
        if(!file.exists()) return ingredientes.toList()
        ObjectInputStream(FileInputStream(file)).use{
            ingredientes = it.readObject() as MutableList<Ingrediente>
        }
        return ingredientes.toList()
    }
}