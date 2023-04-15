package services.storage.hamburguesa

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.AppConfig
import models.Hamburguesa
import mu.KotlinLogging
import java.io.File
import java.io.Serializable

private val logger = KotlinLogging.logger {}

object HamburguesaFileJson: HamburguesaStorageService{

    val localFile = "${AppConfig.APP_DATA}${File.separator}hamburguesa.json"

    override fun saveAll(elements: List<Hamburguesa>): List<Hamburguesa> {
        logger.debug { "HamburguesaFileJson ->\tsaveAll: ${elements.joinToString("\t")}" }

        val file = File(localFile)
        if (file.exists() && !file.canRead()) return emptyList()

        val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter: JsonAdapter<List<Hamburguesa>> =
            moshi.adapter(Types.newParameterizedType(List::class.java, Hamburguesa::class.java))
        val json: String = jsonAdapter.indent("\t").toJson(elements)

        file.writeText(json)

        return elements
    }

    override fun loadAll(): List<Hamburguesa> {
        logger.debug { "HamburguesaFileJson ->\tloadAll" }

        val file = File(localFile)
        if (!file.exists() || !file.canRead()) return emptyList()

        val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter: JsonAdapter<List<Hamburguesa>> =
            moshi.adapter(Types.newParameterizedType(List::class.java, Hamburguesa::class.java))
        val json: String = file.readText()

        return jsonAdapter.fromJson(json) ?: emptyList()
    }
}