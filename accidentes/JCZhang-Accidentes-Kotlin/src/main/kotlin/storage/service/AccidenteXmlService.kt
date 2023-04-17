package storage.service

import mappers.accidenteListToListOfDto
import mappers.toAccidenteList
import models.Accidente
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import java.io.File
import java.nio.file.Files.createFile


object AccidenteXmlService: AccidentesService {

    private val logger = KotlinLogging.logger {  }
    val programPath = System.getProperty("user.dir")
    val serializable = Persister()


    override fun loadAll(): List<Accidente> {
        logger.debug { "AccidenteXml: Cargando Accidentes de fichero xml" }

        val fileXml = File("$programPath${File.separator}data${File.separator}accidentes.xml")
        if(!fileXml.exists()){
            return emptyList()
        }

        val readFiles = serializable.read(dto.AccidenteListDto::class.java, fileXml).toAccidenteList()
        readFiles.forEach { println(it) }
        return readFiles

    }


    override fun saveAll(items: List<Accidente>) {
        logger.debug { "AccidentesXML: Guardando accidentes en fichero xml" }
        val fileXml = File("$programPath${File.separator}data${File.separator}accidentes.xml")
        if (!fileXml.exists()){
            createFile(fileXml.toPath())
        }
        serializable.write(items.accidenteListToListOfDto(), fileXml)
    }
}

