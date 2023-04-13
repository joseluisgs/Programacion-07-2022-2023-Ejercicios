package services.storage.serializable

import config.AppConfig
import models.Ingrediente
import services.storage.IngredienteStorageService
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object IngredienteSerializableService : IngredienteStorageService {
    private val localFile = "${AppConfig.SERIALIZABLE_PATH}${File.separator}ingredientes.ser"

    override fun saveAll(items: List<Ingrediente>) {
        ObjectOutputStream(FileOutputStream(localFile))
            .use { it.writeObject(items) }
    }

    override fun loadAll(): List<Ingrediente> {
        ObjectInputStream(FileInputStream(localFile))
            .use { return it.readObject() as List<Ingrediente> }
    }
}
