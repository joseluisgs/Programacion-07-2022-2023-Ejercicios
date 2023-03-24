package services.storage.ingrediente

import config.AppConfig
import models.Ingrediente
import java.io.File

object IngredienteCsvService : IngredienteStorageService {
    private val localFile = "${AppConfig.CSV_PATH}${File.separator}ingredientes.csv"

    override fun saveAll(items: List<Ingrediente>) {
        File(localFile).writeText(
            "id,nombre,precio\n" +
                    items.joinToString("\n") { it.toString() })
    }

    override fun loadAll(): List<Ingrediente> {
        return File(localFile).useLines {
            it.drop(1).map { line ->
                val (id, nombre, precio) = line.split(",")
                Ingrediente(
                    id = id.toInt(),
                    nombre = nombre,
                    precio = precio.toDouble()
                )
            }.toList()
        }
    }
}