package services.storage.json

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.AppConfig
import exceptions.JsonException
import models.Hamburguesa
import models.dto.HamburguesasDto
import models.dto.toDto
import models.dto.toHamburguesas
import services.storage.HamburguesaStorageService
import java.io.File

@OptIn(ExperimentalStdlibApi::class)
object HamburguesaJsonService : HamburguesaStorageService {
    private val localFile = "${AppConfig.JSON_PATH}${File.separator}hamburguesas.json"
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val adapter = moshi.adapter<HamburguesasDto>()

    override fun saveAll(items: List<Hamburguesa>) {
        File(localFile).writeText(adapter.indent("\t").toJson(items.toDto()))
    }

    override fun loadAll(): List<Hamburguesa> {
        return adapter.fromJson(File(localFile).readText())?.toHamburguesas()
            ?: throw JsonException("Error al leer el fichero JSON")
    }
}