package services.storage.pedido

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapter
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.AppConfig
import models.Bebida
import models.Hamburguesa
import models.Pedido
import models.Producto
import mu.KotlinLogging
import services.storage.json_adapters.LocalDateTimeAdapter
import validator.canReed
import validator.canWrite
import java.io.File

private val logger = KotlinLogging.logger {}
@ExperimentalStdlibApi
object PedidoFileJson : PedidoStorageService{

    private val localFile = "${AppConfig.APP_DATA}${File.separator}pedido.json"
    private val file = File(localFile)
    private val jsonAdapter = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .addLast(LocalDateTimeAdapter())
        .build().adapter<List<Pedido>>()

    override fun saveAll(elements: List<Pedido>): List<Pedido> {
        logger.debug { "PedidoFileJson ->\tsaveAll: ${elements.joinToString("\t")}" }

        if (!canWrite(file)) return emptyList()

        val json = jsonAdapter.indent("\t").toJson(elements)

        file.writeText(json)

        return elements
    }

    override fun loadAll(): List<Pedido> {
        logger.debug { "PedidoFileJson ->\tloadAll" }
        if (!canReed(file)) return emptyList()

        val text = file.readText()

        return jsonAdapter.fromJson(text) ?: emptyList()
    }
}