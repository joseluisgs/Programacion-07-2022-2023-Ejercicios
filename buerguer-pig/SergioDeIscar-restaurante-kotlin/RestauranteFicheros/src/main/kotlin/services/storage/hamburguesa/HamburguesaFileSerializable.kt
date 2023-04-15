package services.storage.hamburguesa

import config.AppConfig
import models.Hamburguesa
import mu.KotlinLogging
import validator.canReed
import validator.canWrite
import java.io.*

private val logger = KotlinLogging.logger {}

object HamburguesaFileSerializable: HamburguesaStorageService {
    private val localFile = "${AppConfig.APP_DATA}${File.separator}hamburguesa.ser"

    override fun saveAll(elements: List<Hamburguesa>): List<Hamburguesa> {
        logger.debug { "HamburguesaFileSerializable ->\tsaveAll: ${elements.joinToString("\t")}" }
        val file = File(localFile)
        if (!canWrite(file)) return emptyList()

        val output = ObjectOutputStream(FileOutputStream(file)).use {
            it.writeObject(elements)
        }

        return elements
    }

    override fun loadAll(): List<Hamburguesa> {
        logger.debug { "HamburguesaFileSerializable ->\tloadAll" }
        val file = File(localFile)
        if (!canReed(file)) return emptyList()

        val hamburguesas = ObjectInputStream(FileInputStream(file)).use {
            it.readObject() as MutableList<Hamburguesa>
        }

        return hamburguesas.toList()
    }
}