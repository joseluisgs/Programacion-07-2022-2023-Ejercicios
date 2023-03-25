package services.storage.hamburguesa

import config.AppConfig
import models.Hamburguesa
import mu.KotlinLogging
import java.io.*

private val logger = KotlinLogging.logger {}

object HamburguesaFileSerializable: HamburguesaStorageService {
    private val localFile = "${AppConfig.APP_DATA}${File.separator}hamburguesa.ser"

    override fun saveAll(elements: List<Hamburguesa>): List<Hamburguesa> {
        logger.debug { "HamburguesaFileSerializable ->\tsaveAll: ${elements.joinToString("\t")}" }
        val file = File(localFile)
        if (file.exists() && !file.canRead()) return emptyList()

        val output = ObjectOutputStream(FileOutputStream(file))
        output.use {
            it.writeObject(elements)
        }

        return elements
    }

    override fun loadAll(): List<Hamburguesa> {
        logger.debug { "HamburguesaFileSerializable ->\tloadAll" }
        val file = File(localFile)
        if (!file.exists() || !file.canRead()) return emptyList()

        val hamburguesas = ObjectInputStream(FileInputStream(file)).use {
            it.readObject() as MutableList<Hamburguesa>
        }

        return hamburguesas.toList()
    }
}