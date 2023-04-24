package services.storage.csv

import config.AppConfig
import models.Ingrediente
import services.storage.IngredienteStorageService
import java.io.File

object IngredienteCsvService : IngredienteStorageService {
    private val path = AppConfig.csvPath + File.separator + "Ingredientes.csv"

    override fun cargar(): List<Ingrediente> {
        return File(path).useLines {
            it.drop(1).map {line ->
                val (id, nombre, precio) = line.split(";")
                Ingrediente(
                    id = id.toInt(),
                    nombre = nombre,
                    precio = precio.toDouble()
                )
            }
        }.toList()
    }

    override fun guardar(items: List<Ingrediente>) {
        File(path).writeText(
            "id;nombre;precio\n" +
            items.joinToString("\n") { it.toString() }
        )
    }
}