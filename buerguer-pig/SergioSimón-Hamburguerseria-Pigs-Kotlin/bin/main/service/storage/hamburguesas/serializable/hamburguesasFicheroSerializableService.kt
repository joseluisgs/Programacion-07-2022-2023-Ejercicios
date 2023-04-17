package service.storage.hamburguesas.serializable

import config.ConfigApp
import models.Hamburguesa
import mu.KotlinLogging
import service.storage.hamburguesas.HamburguesasStorageService
import java.io.*

private val logger = KotlinLogging.logger {}

object hamburguesasFicheroSerializableService : HamburguesasStorageService {

    private val localFile =
        "${ConfigApp.APP_DATA}${File.separator}" + "hamburgesa" + File.separator + "serializable" + File.separator + "hamburgesa.ser"

    override fun saveAll(items: List<Hamburguesa>) {
        logger.info { "Guardando hamburguesas en un fichero serializable" }
        val file = File(localFile)

        val output = ObjectOutputStream(FileOutputStream(file))
        output.use {
            it.writeObject(items)
        }
//        outputStream.writeObject(items)
//        outputStream.close()
    }

    override fun loadAll(): List<Hamburguesa> {
        logger.info { "Cargando hamburguesas de un fichero serializable" }
        val file = File(localFile)
        var hamburguesas = mutableListOf<Hamburguesa>()
        // early return
        if (!file.exists()) return hamburguesas
        val input = ObjectInputStream(FileInputStream(file)).use {
            hamburguesas = it.readObject() as MutableList<Hamburguesa>
        }
        return hamburguesas
    }
}