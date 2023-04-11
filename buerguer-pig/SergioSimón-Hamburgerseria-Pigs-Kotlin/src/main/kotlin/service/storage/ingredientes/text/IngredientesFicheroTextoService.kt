package service.storage.ingredientes.text

import config.ConfigApp
import models.Ingrediente
import mu.KotlinLogging
import service.storage.ingredientes.IngredientesStorageService
import java.io.File

private val logger = KotlinLogging.logger {}


object IngredientesFicheroTextoService : IngredientesStorageService {
    private val localFile =
        "${ConfigApp.APP_DATA}${File.separator}" + "ingredientes" + File.separator + "text" + File.separator + "ingredientes.txt"

    override fun saveAll(items: List<Ingrediente>) {
        logger.info { "Guardando ingredientes en un fichero texto" }
        val file = File(localFile)
        logger.debug { "Iniciando fichero de texto para ingredientes" }
        file.writeText("")
        logger.debug { "Escribiendo en un fichero de texto ingredientes" }
        items.forEach {
            file.appendText(it.id.toString() + "\n")
            file.appendText(it.name + "\n")
            file.appendText(it.price.toString() + "\n")
        }
    }

    override fun loadAll(): List<Ingrediente> {
        logger.info { "Cargando ingredientes desde un fichero de texto" }
        val file = File(localFile)
        val ingrediente = mutableListOf<Ingrediente>()
        // Early return
        if (!file.exists()) return ingrediente

        file.bufferedReader().use {
            while (it.ready()) { // True mientras halla líneas por leer
                val id = it.readLine().toInt()
                val nombre = it.readLine().toString()
                val price = it.readLine().toDouble()
                ingrediente.add(Ingrediente(id, nombre, price))
            }
        }

        return ingrediente.toList()
    }

    // Guardar un fichero de texto como un csv
    fun saveAll2(items: List<Ingrediente>) {
        logger.info { "Guardando un fichero de ingredientes en formato texto de manera alternativa" }
        val file = File(localFile)
        file.writeText("")
        items.forEach {
            file.writeText(it.id.toString() + ":")
            file.appendText(it.name + ":")
            file.appendText(it.price.toString() + "\n")
        }
    }

    // Leer un fichero de texto como un csv
    fun loadAll2(): List<Ingrediente> {
        logger.info { "Guardando un fichero de ingredientes en formato texto de manera alternativa" }
        val file = File(localFile)
        val ingrediente = mutableListOf<Ingrediente>()
        // Early return
        if (!file.exists()) return ingrediente

        return file.readLines()
            .map { line -> line.split(":") }
            .map { camp -> camp.map { it.trim() } }
            .map { camp ->
                Ingrediente(
                    id = camp[0].toInt(),
                    name = camp[1],
                    price = camp[2].toDouble()
                )
            }
    }
}
