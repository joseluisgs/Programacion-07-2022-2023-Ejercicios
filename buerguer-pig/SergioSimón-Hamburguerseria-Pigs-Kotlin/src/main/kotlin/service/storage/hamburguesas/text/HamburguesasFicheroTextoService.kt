package service.storage.hamburguesas.text

import config.ConfigApp
import models.Hamburguesa
import models.Ingrediente
import mu.KotlinLogging
import service.storage.hamburguesas.HamburguesasStorageService
import java.io.File
import java.util.*

private val logger = KotlinLogging.logger {}


object HamburguesasFicheroTextoService : HamburguesasStorageService {
    private val localFile =
        "${ConfigApp.HAMBURGUESA_TEXTO_PATH}${File.separator}" + "hamburguesa.txt"

    override fun saveAll(items: List<Hamburguesa>) {
        logger.info { "Guardando hamburguesas en un fichero de texto" }
        val file = File(localFile)
        logger.debug { "Iniciando fichero de texto para hamburguesas" }
        file.writeText("")
        logger.debug { "Escribiendo en un fichero de texto" }
        items.forEach {
            file.appendText(it.id.toString() + "\n")
            file.appendText(it.name + "\n")
            file.appendText(convertRecetaToWrite(it.receta) + "\n")
            file.appendText(it.precio.toString() + "\n")
        }
    }

    private fun convertRecetaToWrite(receta: List<Ingrediente>): String {
        return receta.joinToString(separator = "|", prefix = "[", postfix = "]") {
            "${it.id};${it.name};${it.price}" // Importante que la separación entre atibutos no sea el mismo que para los atributos de la hamburgesa
        }
    }

    override fun loadAll(): List<Hamburguesa> {
        logger.info { "Cargando hamburguesas desde un fichero de texto" }
        val file = File(localFile)
        val hamburguesas = mutableListOf<Hamburguesa>()
        if (!file.exists()) return hamburguesas

        val lineas = File(localFile).readLines() // Lee y almacena todas las líneas en una lista List<String>

        var i = 0
        while (i < lineas.size) {
            val id = UUID.fromString(lineas[i])
            val name = lineas[i + 1]
            val ingredients = convertRecetaToRead(lineas[i + 2])

            hamburguesas.add(Hamburguesa(id, name, ingredients))
            i += 4
        }

        return hamburguesas.toList()
    }

    private fun convertRecetaToRead(columna: String): List<Ingrediente> {
        return columna
            .replace("[", "")
            .replace("]", "")
            .split("|") // Separación ente ingredientes
            .map { it.split(";") } // Separa los atributos de cada ingrediente
            .map {
                Ingrediente(
                    id = it[0].toInt(),
                    name = it[1],
                    price = it[2].toDouble()
                )
            }
    }
}