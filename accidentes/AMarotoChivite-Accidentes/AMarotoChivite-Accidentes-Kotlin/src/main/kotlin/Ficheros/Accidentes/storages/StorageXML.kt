package Ficheros.Accidentes.storages

import Ficheros.Accidentes.config.ConfigApp
import Ficheros.Accidentes.models.dto.AccidenteDTO
import Ficheros.Accidentes.models.dto.ListAccidentesDTO
import Ficheros.Accidentes.utils.readDataOfCSV
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import java.io.File

class StorageXML : IStorageToImportExport<AccidenteDTO> {

    private val logger = KotlinLogging.logger {}

    val localFile = "${ConfigApp.APP_DATA}${File.separator}AccidentesXML.xml"
    val file = File(localFile)

    private val serializer = Persister()

    override fun saveInFileWithFilter() {
        logger.debug { "Storage: Escribiendo en XML" }

        val listToExport = readDataOfCSV()
        val objectListAccidentesDTO = ListAccidentesDTO()
        objectListAccidentesDTO.myList = listToExport
        serializer.write(objectListAccidentesDTO, file)
    }

    override fun readAllModelsInFile(): List<AccidenteDTO> {
        logger.debug { "Storage: Leyendo desde XML" }

        val listOfAccidentesDTO = serializer.read(ListAccidentesDTO::class.java, file)
        return listOfAccidentesDTO.myList!!
    }
}