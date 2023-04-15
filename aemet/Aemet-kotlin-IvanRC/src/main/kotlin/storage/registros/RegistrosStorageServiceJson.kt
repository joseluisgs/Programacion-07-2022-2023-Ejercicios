package storage.registros

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.ConfigApp
import dto.InformesDto
import mapper.toDto
import mapper.toRegistros
import model.Registro
import mu.KotlinLogging
import java.io.File
import java.time.LocalDate

@ExperimentalStdlibApi
class RegistrosStorageServiceJson {

    private val configApp = ConfigApp
    private val file = File(configApp.APP_DATA+File.separator+"registros.json")
    private val jsonAdapter = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build().adapter<InformesDto>()

    private val logger = KotlinLogging.logger {  }

    fun saveAll(entities: List<Map<LocalDate, Registro>>) {
        logger.debug { "Se guardan todas las mediciones sobre el fichero JSON." }
        file.writeText(jsonAdapter.indent("   ").toJson(entities.toDto()))
    }

    fun loadAll(): List<Map<LocalDate, Registro>> {
        logger.debug { "Se cargan todas las mediciones de el fichero JSON." }
        if(!file.exists()) return emptyList()
        return jsonAdapter.fromJson(file.readText())!!.toRegistros()
    }
}