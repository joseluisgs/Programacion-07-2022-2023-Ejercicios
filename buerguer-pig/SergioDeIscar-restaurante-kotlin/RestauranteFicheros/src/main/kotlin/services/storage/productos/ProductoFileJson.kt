package services.storage.productos

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.AppConfig
import dto.ProductoDto
import locate.moneyToFloat
import models.Bebida
import models.Hamburguesa
import models.Ingrediente
import models.Producto
import mu.KotlinLogging
import services.storage.json_adapters.LocalDateTimeAdapter
import validator.canReed
import validator.canWrite
import java.io.File
import java.lang.RuntimeException

private val logger = KotlinLogging.logger {}

@ExperimentalStdlibApi
object ProductoFileJson: ProductosStorageService {
    private val localFile = "${AppConfig.APP_DATA}${File.separator}productos.json"
    private val file = File(localFile)
    private val jsonAdapter = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .addLast(LocalDateTimeAdapter())
        .build().adapter<List<ProductoDto>>()

    override fun saveAll(elements: List<Producto>): List<Producto> {
        logger.debug { "ProductoFileJson ->\tsaveAll: ${elements.joinToString("\t")}" }
        if (!canWrite(file)) return emptyList()
        val dtoList = elements.map { it.toDto() }
        val json = jsonAdapter.indent("\t").toJson(dtoList)
        file.writeText(json)
        return elements
    }

    override fun loadAll(): List<Producto> {
        logger.debug { "ProductoFileJson ->\tloadAll" }
        if (!canReed(file)) return emptyList()

        val text = file.readText()

        val dtoList = jsonAdapter.fromJson(text) ?: return emptyList()
        return dtoList.map {
            val id = it.id.toInt()
            val nombre = it.nombre
            val precio = it.precio.moneyToFloat()

            when (it.tipo) {
                "Hamburguesa" -> Hamburguesa(
                    id,
                    nombre,
                    it.ingredientes!!.map { i ->
                        Ingrediente(
                            i.id.toInt(),
                            i.nombre,
                            i.precio.moneyToFloat()
                        )
                    }
                )
                "Bebida" -> Bebida(
                    id,
                    nombre,
                    precio,
                    it.capacidad!!.toInt()
                )
                else -> throw RuntimeException("Tipo de producto desconocido")
            }
        }
    }
}