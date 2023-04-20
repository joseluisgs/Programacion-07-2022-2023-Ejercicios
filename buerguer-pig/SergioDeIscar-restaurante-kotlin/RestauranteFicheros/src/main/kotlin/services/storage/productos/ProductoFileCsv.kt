package services.storage.productos

import config.AppConfig
import dto.IngredienteDto
import dto.ProductoDto
import mappers.toClass
import models.Producto
import mu.KotlinLogging
import services.storage.puedoEscribir
import services.storage.puedoLeer
import java.io.File

private val logger = KotlinLogging.logger {  }

object ProductoFileCsv: ProductosStorageService {
    private val localFile ="${AppConfig.APP_DATA}${File.separator}productos.csv"
    override fun saveAll(elements: List<Producto>): List<Producto> {
        logger.debug { "ProductoFileCsv ->\tsaveAll" }
        val file = File(localFile)
        file.puedoEscribir("CSV")
        file.writeText("id,nombre,precio,tipo,ingredientes,capacidad\n")
        elements.forEach {
            file.appendText(it.toCsvRow())
        }
        return elements
    }

    override fun loadAll(): List<Producto> {
        logger.debug { "ProductoFileCsv ->\tloadAll" }
        val file = File(localFile)
        file.puedoLeer("CSV")
        return file.readLines()
            .drop(1)
            .map { it.split(",") }
            .map { it.map { it.trim() } }
            .map { campos ->
                ProductoDto(
                    campos[0],
                    campos[1],
                    campos[2],
                    campos[3],
                    loadIngredientes(campos[4]),
                    campos[5],
                ).toClass()
            }
    }

    private fun loadIngredientes(csv: String): List<IngredienteDto>?{
        if (csv.trim().isEmpty()) return null
        return csv
            .split("|")
            .map { it.split(";") }
            .map { IngredienteDto(it[0],it[1],it[2]) }
    }
}