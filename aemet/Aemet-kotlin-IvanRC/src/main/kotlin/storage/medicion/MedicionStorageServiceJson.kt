package storage.medicion

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.ConfigApp
import dto.MapaMedicionesDto
import mapper.toMediciones
import mapper.toMedicionesDto
import model.Medicion
import mu.KotlinLogging
import java.io.File
import java.time.LocalDate

@ExperimentalStdlibApi
class MedicionStorageServiceJson {

    private val configApp = ConfigApp
    private val file = File(configApp.APP_DATA+File.separator+"mediciones.json")
    private val jsonAdapter = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build().adapter<MapaMedicionesDto>()

    private val logger = KotlinLogging.logger {  }

    fun saveAll(entities: Map<LocalDate, List<Medicion>>) {
        logger.debug { "Se guardan todas las mediciones sobre el fichero JSON." }
        file.writeText(jsonAdapter.indent("   ").toJson(entities.toMedicionesDto()))
    }

    fun loadAll(): Map<LocalDate, List<Medicion>> {
        logger.debug { "Se cargan todas las mediciones de el fichero JSON." }
        return jsonAdapter.fromJson(file.readText())!!.toMediciones()
    }
}