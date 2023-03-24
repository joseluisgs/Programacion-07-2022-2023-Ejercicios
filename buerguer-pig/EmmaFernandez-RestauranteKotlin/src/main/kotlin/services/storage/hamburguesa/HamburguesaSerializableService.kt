package services.storage.hamburguesa

import config.AppConfig
import models.Hamburguesa
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object HamburguesaSerializableService : HamburguesaStorageService {
    private val localFile = "${AppConfig.SERIALIZABLE_PATH}${File.separator}hamburguesas.ser"
    override fun saveAll(items: List<Hamburguesa>) {
        val oos = ObjectOutputStream(FileOutputStream(localFile))
        oos.use { it.writeObject(items) }
    }

    override fun loadAll(): List<Hamburguesa> {
        val ois = ObjectInputStream(FileInputStream(localFile))
        ois.use { return it.readObject() as List<Hamburguesa> }
    }
}
