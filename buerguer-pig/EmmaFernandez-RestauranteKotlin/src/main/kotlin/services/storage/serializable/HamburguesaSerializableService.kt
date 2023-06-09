package services.storage.serializable

import config.AppConfig
import models.Hamburguesa
import services.storage.HamburguesaStorageService
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object HamburguesaSerializableService : HamburguesaStorageService {
    private val localFile = "${AppConfig.SERIALIZABLE_PATH}${File.separator}hamburguesas.ser"
    override fun saveAll(items: List<Hamburguesa>) {
        ObjectOutputStream(FileOutputStream(localFile))
            .use { it.writeObject(items) }
    }

    override fun loadAll(): List<Hamburguesa> {
        ObjectInputStream(FileInputStream(localFile))
            .use { return it.readObject() as List<Hamburguesa> }
    }
}
