package services.storage.ingredientes

import models.Ingrediente
import mu.KotlinLogging
import java.io.File
import java.nio.file.Files.createFile


object IngredientesBinarioService: IngredientesStorageService {

    private val logger = KotlinLogging.logger {  }

    val programPath = System.getProperty("user.dir")

    val filePath = "$programPath${File.separator}data${File.separator}ingredientes.bin"

    override fun saveAll(items: List<Ingrediente>) {

        logger.debug { "IngredientesBinario: Guardando ingredientes en fichero binario" }
        val file = File(filePath)

        if (!file.exists()){
            createFile(file.toPath())
        }

        val insertedData = file.outputStream().buffered().use{
            items.forEach {item ->
                it.write(item.ID.toString().toByteArray() + "\n".toByteArray())
                it.write(item.nombre.toByteArray() + "\n".toByteArray())
                it.write(item.precio.toString().toByteArray() + "\n".toByteArray())
            }
        }
        println(insertedData)


    }

    override fun loadAll(): List<Ingrediente> {
        TODO("Not yet implemented")
    }
}