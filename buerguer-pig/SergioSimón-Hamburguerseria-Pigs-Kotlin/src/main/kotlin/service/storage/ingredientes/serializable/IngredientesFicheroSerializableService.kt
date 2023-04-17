package service.storage.ingredientes.serializable

import config.ConfigApp
import models.Ingrediente
import mu.KotlinLogging
import service.storage.ingredientes.IngredientesStorageService
import java.io.*

private val logger = KotlinLogging.logger {}

object IngredientesFicheroSerializableService : IngredientesStorageService {
    private val localFile =
        "${ConfigApp.INGREDIENTE_SERIALIZABLE_PATH}${File.separator}" + "ingredientes.ser"

    override fun saveAll(items: List<Ingrediente>) {
        logger.info { "Guardando ingredientes en un fichero serializable" }
        val file = File(localFile)

        val outputStream = ObjectOutputStream(FileOutputStream(file))
        outputStream.use {
            it.writeObject(items)
        }
//        outputStream.writeObject(items)
//        outputStream.close()
    }

    override fun loadAll(): List<Ingrediente> {
        logger.info { "Cargando productos desde fichero de serializable" }
        val file = File(localFile)
        var productos = mutableListOf<Ingrediente>()
        // early return
        if (!file.exists()) return productos
        val input = ObjectInputStream(FileInputStream(file)).use {
            productos = it.readObject() as MutableList<Ingrediente>
        }
        return productos
    }
}