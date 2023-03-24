package services.storage.ingrediente

import config.AppConfig
import models.Ingrediente
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object IngredienteSerializableService : IngredienteStorageService {
    private val localFile = "${AppConfig.SERIALIZABLE_PATH}${File.separator}ingredientes.ser"

    override fun saveAll(items: List<Ingrediente>) {
        val oos = ObjectOutputStream(FileOutputStream(localFile))
        oos.use { it.writeObject(items) }
    }

    override fun loadAll(): List<Ingrediente> {
        val ois = ObjectInputStream(FileInputStream(localFile))
        ois.use { return it.readObject() as List<Ingrediente> }
    }
}
