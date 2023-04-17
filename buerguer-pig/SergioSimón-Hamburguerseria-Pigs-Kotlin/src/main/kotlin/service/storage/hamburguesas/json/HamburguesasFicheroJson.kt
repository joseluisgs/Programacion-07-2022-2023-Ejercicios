package service.storage.hamburguesas.json

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.ConfigApp
import models.Hamburguesa
import mu.KotlinLogging
import service.storage.hamburguesas.HamburguesasStorageService
import utils.UuidAdapter
import utils.toPrettyJson
import java.io.File

private val logger = KotlinLogging.logger {}

@ExperimentalStdlibApi
object HamburguesasFicheroJson : HamburguesasStorageService {
    private val localFile =
        "${ConfigApp.HAMBURGUESA_JSON_PATH}${File.separator}" + "hamburguesa.json"

    private val moshi = Moshi.Builder()
        .add(UuidAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val jsonAdapter = moshi.adapter<List<Hamburguesa>>()

    override fun saveAll(items: List<Hamburguesa>) {
        logger.info { "Guardando hamburguesas en un fichero Json" }
        val file = File(localFile)
        file.writeText(jsonAdapter.toPrettyJson(items))
    }

    override fun loadAll(): List<Hamburguesa> {
        logger.info { "Cargando hamburguesa desde fichero de json" }
        val file = File(localFile)
        return jsonAdapter.fromJson(file.readText()) ?: emptyList()
    }
}