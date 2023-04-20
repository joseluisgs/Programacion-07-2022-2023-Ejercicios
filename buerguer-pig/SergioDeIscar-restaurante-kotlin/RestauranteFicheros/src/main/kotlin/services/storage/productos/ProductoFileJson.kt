package services.storage.productos

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.AppConfig
import dto.ProductoDto
import mappers.toClass
import mappers.toDto
import models.Producto
import mu.KotlinLogging
import services.storage.puedoEscribir
import services.storage.puedoLeer
import java.io.File
import java.io.IOException

private val logger = KotlinLogging.logger {}

@ExperimentalStdlibApi
object ProductoFileJson: ProductosStorageService {
    private val localFile = "${AppConfig.APP_DATA}${File.separator}productos.json"
    private val jsonAdapter = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build().adapter<List<ProductoDto>>()

    override fun saveAll(elements: List<Producto>): List<Producto> {
        logger.debug { "ProductoFileJson ->\tsaveAll: ${elements.joinToString("\t")}" }
        val file = File(localFile)
        file.puedoEscribir("JSON")
        val json = jsonAdapter.indent("\t").toJson(elements.map { it.toDto() })
        file.writeText(json)
        return elements
    }

    override fun loadAll(): List<Producto> {
        logger.debug { "ProductoFileJson ->\tloadAll" }
        val file = File(localFile)
        file.puedoLeer("JSON")
        return jsonAdapter.fromJson(file.readText())?.map { it.toClass() } ?: emptyList()
    }
}