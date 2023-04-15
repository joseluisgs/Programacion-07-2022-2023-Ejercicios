package services.storage.hamburguesas

import models.Hamburguesa
import mu.KotlinLogging
import java.io.File
import java.nio.file.Files.createFile

object HamburguesaBinarioService: HamburguesaStorageService {

    private val logger = KotlinLogging.logger {  }
    private val programPath: String = System.getProperty("user.dir")

    private val filePath = "$programPath${File.separator}data${File.separator}hamburguesas.bin"

    override fun saveAll(items: List<Hamburguesa>) {
        val file = File(filePath)

        if (!file.exists()){
            createFile(file.toPath())
        }

        val insertedData = file.outputStream().use{
            items.forEach { item ->
                it.write(item.id.toString().toByteArray() +"\n".toByteArray())
                it.write(item.nombre.toByteArray() +"\n".toByteArray())
                it.write(item.ingredientes.joinToString("|").toByteArray() +"\n".toByteArray())
                it.write(item.precio.toString().toByteArray() +"\n".toByteArray())
            }
        }

        println(insertedData)
    }

    override fun loadAll(): List<Hamburguesa> {
        TODO("Not yet implemented")
    }
}