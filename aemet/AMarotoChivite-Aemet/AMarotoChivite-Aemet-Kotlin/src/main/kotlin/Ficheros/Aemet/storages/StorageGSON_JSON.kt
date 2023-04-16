package Ficheros.Aemet.storages

import Ficheros.Aemet.config.ConfigApp
import Ficheros.Aemet.models.Aemet
import Ficheros.Aemet.models.AemetDailyConsultToExport
import Ficheros.Aemet.utils.groupDayWithFilter
import com.google.gson.GsonBuilder
import mu.KotlinLogging
import java.io.File

@ExperimentalStdlibApi
class StorageGSON_JSON : IStorageToExport<Aemet> {

    private val logger = KotlinLogging.logger {}
    val localFile = "${ConfigApp.APP_DATA}${File.separator}AemetDaily_GSON.json"
    val file = File(localFile)

    /**
     * Escribimos el JSON el mapa POR CADA DÍA con las consultas a continuación
     * - Temperatura media
     * - Temperatura máxima (Lugar y momento)
     * - Temperatura mínima (Lugar y momento)
     * - Si hubo precipitación (sí/no) y valor de la misma.
     */
    override fun saveInFileWithFilter(repository: List<Aemet>) {
        logger.debug { "Storage: Escribiendo en JSON" }

        val dailyWeatherMap: MutableMap<String, AemetDailyConsultToExport> = groupDayWithFilter(repository)

        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonString = gson.toJson(dailyWeatherMap)
        file.writeText(jsonString)
    }
}