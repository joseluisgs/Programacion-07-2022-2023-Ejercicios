package service.storage.ingredientes.json

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.ConfigApp
import models.Ingrediente
import mu.KotlinLogging
import service.storage.ingredientes.IngredientesStorageService
import utils.UuidAdapter
import utils.toPrettyJson
import java.io.File

private val logger = KotlinLogging.logger {}

@ExperimentalStdlibApi // Para usar el método DE adapter con enlazado de Kotlin
object IngredientesFicheroJsonService : IngredientesStorageService {
    private val localFile =
        "${ConfigApp.APP_DATA}${File.separator}" + "ingredientes" + File.separator + "json" + File.separator + "ingredientes.json"

    private val moshi = Moshi.Builder()
        .add(UuidAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val jsonAdpapter = moshi.adapter<List<Ingrediente>>()

    override fun saveAll(items: List<Ingrediente>) {
        logger.info { "Guardando ingredientes en un fichero json" }
        val file = File(localFile)
        file.writeText(jsonAdpapter.toPrettyJson(items))
    }

    override fun loadAll(): List<Ingrediente> {
        logger.info { "Cargando ingrediente desde fichero de json" }
        val file = File(localFile)
        return jsonAdpapter.fromJson(file.readText()) ?: emptyList()
    }
}