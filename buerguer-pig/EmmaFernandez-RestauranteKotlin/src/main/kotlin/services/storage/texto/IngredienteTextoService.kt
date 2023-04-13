package services.storage.texto

import config.AppConfig
import models.Ingrediente
import services.storage.IngredienteStorageService
import java.io.File

object IngredienteTextoService : IngredienteStorageService {
    private val localFile = "${AppConfig.TEXT_PATH}${File.separator}ingredientes.txt"

    override fun saveAll(items: List<Ingrediente>) {
        File(localFile).writeText(items.joinToString("\n") { it.toString() })
    }

    override fun loadAll(): List<Ingrediente> {
        return File(localFile).useLines {
            it.map { line ->
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