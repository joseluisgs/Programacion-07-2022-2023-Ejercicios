package services.storage.json

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import config.AppConfig
import dto.HamburguesasDto
import dto.toDto
import dto.toHamburguesas
import exceptions.JsonException
import models.Hamburguesa
import services.storage.HamburguesaStorageService
import java.io.File

@OptIn(ExperimentalStdlibApi::class)
object HamburguesaJsonService : HamburguesaStorageService {
    val path = AppConfig.jsonPath + File.separator + "Hamburguesas.json"
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    val adapter = moshi.adapter<HamburguesasDto>()
    override fun cargar(): List<Hamburguesa> {
        return adapter.fromJson(File(path).readText())?.toHamburguesas() ?: throw JsonException("Error lectura de JSON")
    }

    override fun guardar(items: List<Hamburguesa>) {
        File(path).writeText(adapter.indent("\t").toJson(items.toDto()))
    }
}
