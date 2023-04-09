package services.storage.ingredientes


import models.Ingrediente
import mu.KotlinLogging
import java.io.*
import java.nio.file.Files

object IngredienteSerService: IngredientesStorageService {
    private val logger = KotlinLogging.logger {  }
    private val programPath = System.getProperty("user.dir")
    private val filePath =("$programPath${File.separator}data${File.separator}ingredientes.ser")

    override fun saveAll(items: List<Ingrediente>) {
        logger.debug { "HamburguesaSer: Guardando Hamburguesas en fichero serializable" }
        val file = File(filePath)
        if (!file.exists()){
            Files.createFile(file.toPath())
        }

        val output = ObjectOutputStream(FileOutputStream(file))
        output.use {
            it.writeObject(items)
        }

    }

    override fun loadAll(): List<Ingrediente> {
        logger.debug { "HamburguesaSer: Cargando hamburguesas desde fichero serializable" }
        val file = File(filePath)
        var ingredientes: MutableList<Ingrediente>

        val input = ObjectInputStream(FileInputStream(file))
        input.use {
            ingredientes = it.readObject() as MutableList<Ingrediente>
        }
        return ingredientes
    }
}