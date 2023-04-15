package services.storage.hamburguesas

import models.Hamburguesa
import mu.KotlinLogging
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.nio.file.Files.createFile

object HamburguesaSerializableService: HamburguesaStorageService {
    private val logger = KotlinLogging.logger {  }
    private val programPath = System.getProperty("user.dir")
    private val filePath = "$programPath${File.separator}data${File.separator}hamburguesa.ser"



    override fun saveAll(items: List<Hamburguesa>) {
        logger.debug { "HamburguesaSer: Guardando Hamburguesas en fichero serializable" }
        val file = File(filePath)
        if (!file.exists()){
            createFile(file.toPath())
        }

        val output = ObjectOutputStream(FileOutputStream(file))
        output.use {
            it.writeObject(items)
        }

    }

    override fun loadAll(): List<Hamburguesa> {
        logger.debug { "HamburguesaSer: Cargando hamburguesas desde fichero serializable" }
        val file = File(filePath)
        var burguers = mutableListOf<Hamburguesa>()

        val input = ObjectInputStream(FileInputStream(file))
        input.use {
            burguers = it.readObject() as MutableList<Hamburguesa>
        }
        return burguers
    }
}