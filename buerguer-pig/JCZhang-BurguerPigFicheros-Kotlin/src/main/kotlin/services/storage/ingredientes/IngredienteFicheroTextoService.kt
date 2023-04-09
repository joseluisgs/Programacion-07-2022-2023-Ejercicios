package services.storage.ingredientes

import models.Ingrediente
import mu.KotlinLogging
import java.io.File
import java.nio.file.Files.createFile

object IngredienteFicheroTextoService: IngredientesStorageService {

    private val logger = KotlinLogging.logger{}
    private val programPath = System.getProperty("user.dir")
    private val filePath =("$programPath${File.separator}data${File.separator}ingredientes.txt")

    override fun saveAll(items: List<Ingrediente>) {
        logger.debug("Service: Guardando ingredientes en fichero de texto")
        val file = File(filePath)
        if (!file.exists()) {
            createFile(file.toPath())
        }
        file.writeText("")
        items.forEach{
            file.appendText(it.ID.toString() + "\n")
            file.appendText(it.nombre + "\n")
            file.appendText(it.precio.toString() + "\n")
        }
    }

    override fun loadAll(): List<Ingrediente> {
        logger.debug("Service: Cargando ingredientes del fichero de texto")
        val file = File(filePath)
        val ingredientes = mutableListOf<Ingrediente>()
        file.bufferedReader().use {
            while (it.ready()){
                val id = it.readLine().toInt()
                val nombre = it.readLine().trim()
                val precio = it.readLine().toDouble()
                ingredientes.add(Ingrediente(id,nombre,precio))
            }

        }
        ingredientes.forEach { println(it) }
        return ingredientes
    }
}