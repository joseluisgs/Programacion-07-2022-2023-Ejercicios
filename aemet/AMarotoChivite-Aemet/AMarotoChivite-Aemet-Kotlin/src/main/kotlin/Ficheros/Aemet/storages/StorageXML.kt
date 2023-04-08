package Ficheros.Aemet.storages

import Ficheros.Aemet.config.ConfigApp
import Ficheros.Aemet.models.Aemet
import Ficheros.Aemet.models.AemetDailyConsultToExport
import Ficheros.Aemet.models.dto.AemetDailyMapToExportXML
import Ficheros.Aemet.utils.groupDayWithFilter
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import java.io.File

class StorageXML : IStorageToExport<Aemet> {

    private val logger = KotlinLogging.logger {}

    private val localFile = "${ConfigApp.APP_DATA}${File.separator}AemetDailyXML.xml"
    private val file = File(localFile)

    private val serializer = Persister()

    override fun saveInFileWithFilter(repository: List<Aemet>) {
        logger.debug { "Storage: Escribiendo en XML" }

        val dailyWeatherMap: MutableMap<String, AemetDailyConsultToExport> = groupDayWithFilter(repository)

        val myObjectMap = AemetDailyMapToExportXML()
        myObjectMap.myMap = dailyWeatherMap
        serializer.write(myObjectMap, file)
    }
}