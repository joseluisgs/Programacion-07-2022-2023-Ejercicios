package services.storage.serializable

import config.AppConfig
import models.Hamburguesa
import services.storage.HamburguesaStorageService
import java.io.*

object HamburguesaSerializableService : HamburguesaStorageService {
    val path = AppConfig.serializablePath + File.separator + "Hamburguesa.ser"
    override fun cargar(): List<Hamburguesa> {
        return ObjectInputStream(FileInputStream(File(path)))
            .readObject() as List<Hamburguesa>
    }

    override fun guardar(items: List<Hamburguesa>) {
        ObjectOutputStream(FileOutputStream(File(path)))
            .writeObject(items)
    }
}
