package storageService.Hamburger

import config.ConfigApp
import model.Hamburgesa
import model.Ingrediente
import mu.KotlinLogging
import java.io.*

class HamburgesaStorageServiceSerializable: HamburgesaStorageService {

    private val config = ConfigApp
    private val file = File(config.APP_DATA+ File.separator+"hamburgesa.ser")
    private val logger = KotlinLogging.logger {}

    override fun saveAll(entities: List<Hamburgesa>) {
        logger.debug { "Se guardan todos las hamburgesas en el fichero serializable." }
        ObjectOutputStream(FileOutputStream(file)).use{
            it.writeObject(entities)
        }
    }

    override fun loadAll(): List<Hamburgesa> {
        logger.debug { "Se cargán todos las hamburgesas del fichero serializable." }
        var hamburgesas = mutableListOf<Hamburgesa>()
        if(!file.exists()) return hamburgesas.toList()
        ObjectInputStream(FileInputStream(file)).use{
            hamburgesas = it.readObject() as MutableList<Hamburgesa>
        }
        return hamburgesas.toList()
    }
}