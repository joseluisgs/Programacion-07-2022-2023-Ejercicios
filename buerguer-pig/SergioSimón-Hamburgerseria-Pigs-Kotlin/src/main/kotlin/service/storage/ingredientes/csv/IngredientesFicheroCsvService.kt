package service.storage.ingredientes.csv

import config.ConfigApp
import models.Ingrediente
import mu.KotlinLogging
import service.storage.ingredientes.IngredientesStorageService
import java.io.File

private val logger = KotlinLogging.logger {}

object IngredientesFicheroCsvService : IngredientesStorageService {
    private val localFile =
        "${ConfigApp.APP_DATA}${File.separator}" + "ingredientes" + File.separator + "csv" + File.separator + "ingredientes.csv"

    override fun saveAll(items: List<Ingrediente>) {
        logger.info { "Guardando ingredientes en un fichero csv" }
        val file = File(localFile)
        // Escribir el encabezado
        file.writeText("id,nombre,precio" + "\n")
        items.forEach {
            file.appendText("${it.id},${it.name},${it.price}" + "\n")
        }
    }

    override fun loadAll(): List<Ingrediente> {
        logger.info { "Cargando los ingredientes de un fichero csv" }
        val file = File(localFile)
        // Early return
        if (!file.exists()) return emptyList()
        return file.readLines()
            .drop(1) // Se salta la primera linea del archivo
            .map { linea -> linea.split(",") }
            .map { campos -> campos.map { it.trim() } } // trim() elimina espacios en blanco
            .map { campos ->
                Ingrediente(
                    id = campos[0].toInt(),
                    name = campos[1],
                    price = campos[2].toDouble()
                )
            }
    }
}