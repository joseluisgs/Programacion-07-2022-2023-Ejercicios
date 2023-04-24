package services.storage.texto

import config.AppConfig
import models.Ingrediente
import services.storage.IngredienteStorageService
import java.io.File

object IngredienteTextoService : IngredienteStorageService {
    private val path = "${AppConfig.textoPath}${File.separator}Ingredientes.txt"

    override fun cargar(): List<Ingrediente> {
        return File(path).useLines { lines ->
            lines.map { line ->
                val (id, nombre, precio) = line.split(";")
                Ingrediente(
                    id = id.toInt(),
                    nombre = nombre,
                    precio = precio.toDouble()
                )
            }.toList()
        }
    }

    override fun guardar(items: List<Ingrediente>) {
        File(path).writeText(
            items.joinToString("\n") { it.toString() }
        )
    }
}