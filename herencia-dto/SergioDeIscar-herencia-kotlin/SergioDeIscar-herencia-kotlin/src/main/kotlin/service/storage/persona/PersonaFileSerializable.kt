package service.storage.persona

import config.AppConfig
import models.Persona
import mu.KotlinLogging
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

private val logger = KotlinLogging.logger{ }

object PersonaFileSerializable: PersonaStorageService {
    private val localFile = "${AppConfig.APP_DATA}${File.separator}persona.ser"

    override fun saveAll(elements: List<Persona>): List<Persona> {
        logger.debug { "PersonaFileSerializable ->\tsaveAll" }
        val file = File(localFile)
        if (file.exists() && !file.canRead()) return emptyList()
        ObjectOutputStream(FileOutputStream(file)).use {
            it.writeObject(elements)
        }
        return elements
    }

    override fun loadAll(): List<Persona> {
        logger.debug { "PersonaFileSerializable ->\tloadAll" }
        val file = File(localFile)
        if (!file.exists() || !file.canRead()) return emptyList()
        return ObjectInputStream(FileInputStream(file)).use {
            it.readObject() as MutableList<Persona>
        }.toList()
    }
}