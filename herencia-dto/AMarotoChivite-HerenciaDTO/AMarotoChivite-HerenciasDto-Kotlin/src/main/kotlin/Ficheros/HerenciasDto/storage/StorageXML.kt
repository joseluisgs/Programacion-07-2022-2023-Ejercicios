package Ficheros.HerenciasDto.storage

import Ficheros.HerenciasDto.config.ConfigApp
import Ficheros.HerenciasDto.models.dto.ListOfPersonDTO
import Ficheros.HerenciasDto.models.dto.PersonDTO
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import java.io.File

class StorageXML : IStorageGeneral<PersonDTO> {

    private val logger = KotlinLogging.logger {}

    private val localFile = "${ConfigApp.APP_DATA}${File.separator}personXML.xml"

    private val serializer = Persister()

    override fun saveInFile(repository: List<PersonDTO>) {
        logger.debug { "Storage: Escribiendo (sobreescribiendo) en XML" }
        val file = File(localFile)

        // Instanciamos un DTO que almacene nuestra lista para poder escribir en XML
        val myObject = ListOfPersonDTO()
        myObject.myList = repository
        serializer.write(myObject, file)
    }

    override fun readAllModelsInFile(): List<PersonDTO> {
        logger.debug { "Storage: Leyendo desde fichero de XML" }
        val file = File(localFile)

        val listOfPersonDTO = serializer.read(ListOfPersonDTO::class.java, file)
        return listOfPersonDTO.myList!!
    }
}