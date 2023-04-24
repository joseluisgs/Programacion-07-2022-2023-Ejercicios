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
    private val path = AppConfig.serializablePath + File.separator + "Ingredientes.ser"

    override fun cargar(): List<Ingrediente> {
        return ObjectInputStream(FileInputStream(File(path)))
            .readObject() as List<Ingrediente>
    }

    override fun guardar(items: List<Ingrediente>) {
        ObjectOutputStream(FileOutputStream(File(path)))
            .writeObject(items)
    }
}