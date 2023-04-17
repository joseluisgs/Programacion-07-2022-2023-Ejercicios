package service.storage.hamburguesas.csv

import config.ConfigApp
import models.Hamburguesa
import models.Ingrediente
import mu.KotlinLogging
import service.storage.hamburguesas.HamburguesasStorageService
import java.io.File
import java.util.*

private val logger = KotlinLogging.logger {}

object HamburguesasFicheroCsvService : HamburguesasStorageService {

    private val localFile =
        "${ConfigApp.HAMBURGUESA_CSV_PATH}${File.separator}" + "hamburguesa.csv"

    override fun saveAll(items: List<Hamburguesa>) {
        logger.info { "Guardando hamburguesas en un fichero csv" }
        val file = File(localFile)
        // Escribir el encabezado
        file.writeText("id,nombre,ingredientes,precio" + "\n")
        items.forEach {
            file.appendText("${it.id},${it.name},${convertRecetaToWrite(it.receta)},${it.precio}" + "\n") // Recuerda siempre añadir las comas y un salto de línea
        }
    }

    private fun convertRecetaToWrite(receta: List<Ingrediente>): String {
        return receta.joinToString(separator = "|", prefix = "[", postfix = "]") {
            "${it.id};${it.name};${it.price}" // Importante que la separación entre atibutos no sea el mismo que para los atributos de la hamburgesa
        }
    }

    override fun loadAll(): List<Hamburguesa> {
        logger.info { "Cargando hamburguesas en un fichero csv" }
        val file = File(localFile)
        if (!file.exists() && !file.canRead()) return emptyList()

        return file.readLines()
            .drop(1)
            .map { linea -> linea.split(",") } // Separa los atributos los cuales tengan ","
            .map { campos -> campos.map { it.trim() } } // trim() elimina espacios en blanco. Entre los atributos no debemos escribir con espacios
            .map { campos ->
                Hamburguesa(
                    id = UUID.fromString(campos[0]), // Importante convertir el String a un UUID sobre todo si no tenemos que pasarlo por constructor
                    name = campos[1],
                    receta = convertRecetaToRead(campos[2]), // Convierte el String en un List<Ingrediente>
                    //precio = campos[3].toDouble(),
                )
            }
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

