package services.storage.hamburguesa

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.AppConfig
import models.Hamburguesa
import mu.KotlinLogging
import validator.canReed
import validator.canWrite
import java.io.File
import java.io.Serializable

private val logger = KotlinLogging.logger {}

@ExperimentalStdlibApi
object HamburguesaFileJson: HamburguesaStorageService{

    private val localFile = "${AppConfig.APP_DATA}${File.separator}hamburguesa.json"

    override fun saveAll(elements: List<Hamburguesa>): List<Hamburguesa> {
        logger.debug { "HamburguesaFileJson ->\tsaveAll: ${elements.joinToString("\t")}" }

        val file = File(localFile)
        if (!canWrite(file)) return emptyList()

        val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
       /* val jsonAdapter: JsonAdapter<List<Hamburguesa>> =
            moshi.adapter(Types.newParameterizedType(List::class.java, Hamburguesa::class.java))*/
        val jsonAdapter = moshi.adapter<List<Hamburguesa>>()
        val json: String = jsonAdapter.indent("\t").toJson(elements)

        file.writeText(json)

        return elements
    }

    override fun loadAll(): List<Hamburguesa> {
        logger.debug { "HamburguesaFileJson ->\tloadAll" }

        val file = File(localFile)
        if (!canReed(file)) return emptyList()

        val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter: JsonAdapter<List<Hamburguesa>> =
            moshi.adapter(Types.newParameterizedType(List::class.java, Hamburguesa::class.java))
        val json: String = file.readText()

        return jsonAdapter.fromJson(json) ?: emptyList()
    }
}