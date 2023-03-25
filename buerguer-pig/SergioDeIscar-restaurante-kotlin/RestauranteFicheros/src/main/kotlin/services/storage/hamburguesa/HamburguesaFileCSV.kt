package services.storage.hamburguesa

import config.AppConfig
import models.Hamburguesa
import models.Ingrediente
import mu.KotlinLogging
import validator.canReed
import validator.canWrite
import java.io.File

private val logger = KotlinLogging.logger {}

object HamburguesaFileCSV: HamburguesaStorageService {
    private val localFile = "${AppConfig.APP_DATA}${File.separator}hamburguesa.csv"

    override fun saveAll(elements: List<Hamburguesa>): List<Hamburguesa> {
        logger.debug { "HamburguesaFileCSV ->\tsaveAll: ${elements.joinToString("\t")}" }

        val file = File(localFile)
        if (!canWrite(file)) return emptyList()

        // Encabezado
        file.writeText("id,nombre,precio,ingredientes\n")

        elements.forEach {
            file.appendText("${it.id},${it.nombre},${it.precio},${fromIngredientesToCsvRow(it.ingredientes)}\n")
        }

        return elements
    }

    private fun fromIngredientesToCsvRow(ingredientes: List<Ingrediente>): String {
        return ingredientes.joinToString(separator = "|"){
            "${it.id};${it.nombre};${it.precio}"
        }
    }

    override fun loadAll(): List<Hamburguesa> {
        logger.debug { "HamburguesaFileCSV ->\tloadAll" }

        val file = File(localFile)
        if(!canReed(file)) return emptyList()

        // Lee el fichero completo
        return file.readLines()
            .drop(1)
            .map { row -> row.split(",") }
            .map {
                Hamburguesa(
                    id = it[0].toInt(),
                    nombre = it[1],
                    ingredientes = fromCsvRowToIngredientes(it[3]).toMutableList()
                )
            }
    }

    private fun fromCsvRowToIngredientes(row: String): List<Ingrediente> {
        return row
            .split("|")
            .map { it.split(";") }
            .map {
                Ingrediente(
                    id = it[0].toInt(),
                    nombre = it[1],
                    precio = it[2].toFloat()
                )
            }
    }
}