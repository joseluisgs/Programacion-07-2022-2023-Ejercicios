package services.storage.hamburguesas

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import models.Hamburguesa

import mu.KotlinLogging
import utils.UuidAdapter

import utils.toPrettyJson
import java.io.File
import java.nio.file.Files.createFile


@ExperimentalStdlibApi
object HamburguesaJsonService: HamburguesaStorageService {

    private val logger = KotlinLogging.logger {  }
    private val programPath = System.getProperty("user.dir")
    private val filePath = "$programPath${File.separator}data${File.separator}hamburguesa.json"

    private val moshi = Moshi.Builder()
        .add(UuidAdapter())
        .addLast(KotlinJsonAdapterFactory()).build()

    private val jsonAdapter = moshi.adapter<List<Hamburguesa>>()

    override fun saveAll(items: List<Hamburguesa>) {
        logger.debug { "HamburguesaJson: Guardando hamburguesas en fichero Json" }
        val file = File(filePath)
        if (!file.exists()){
            createFile(file.toPath())
        }

        file.writeText(jsonAdapter.toPrettyJson(items))
    }

    override fun loadAll(): List<Hamburguesa> {
        logger.debug { "HamburguesaJSon: Cargando hamburguesas desde fichero Json" }
        val file = File(filePath)

        return jsonAdapter.fromJson(file.readText())!!
    }
}