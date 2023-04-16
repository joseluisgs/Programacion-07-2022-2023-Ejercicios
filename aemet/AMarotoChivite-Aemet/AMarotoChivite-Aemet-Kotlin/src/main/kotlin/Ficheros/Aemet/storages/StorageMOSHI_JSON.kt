package Ficheros.Aemet.storages

import Ficheros.Aemet.config.ConfigApp
import Ficheros.Aemet.models.Aemet
import Ficheros.Aemet.models.AemetDailyConsultToExport
import Ficheros.Aemet.utils.groupDayWithFilter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import mu.KotlinLogging
import java.io.File

@ExperimentalStdlibApi
class StorageMOSHI_JSON : IStorageToExport<Aemet> {

    private val logger = KotlinLogging.logger {}
    private val localFile = "${ConfigApp.APP_DATA}${File.separator}AemetDaily_MOSHI.json"
    private val file = File(localFile)

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val mapType =
        Types.newParameterizedType(Map::class.java, String::class.java, AemetDailyConsultToExport::class.java)
    private val jsonAdapter: JsonAdapter<Map<String, AemetDailyConsultToExport>> = moshi.adapter(mapType)

    /**
     * Escribimos el JSON el mapa POR CADA DÍA con las consultas a continuación
     * - Temperatura media
     * - Temperatura máxima (Lugar y momento)
     * - Temperatura mínima (Lugar y momento)
     * - Si hubo precipitación (sí/no) y valor de la misma.
     */
    override fun saveInFileWithFilter(repository: List<Aemet>) {
        logger.debug { "Storage: Escribiendo en JSON con MOSHI" }

        val dailyWeatherMap: MutableMap<String, AemetDailyConsultToExport> = groupDayWithFilter(repository)

        file.writeText(jsonAdapter.indent("  ").toJson(dailyWeatherMap))
    }

}