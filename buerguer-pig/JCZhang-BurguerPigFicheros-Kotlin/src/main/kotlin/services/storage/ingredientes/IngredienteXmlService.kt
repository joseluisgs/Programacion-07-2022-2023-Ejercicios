package services.storage.ingredientes

import mappers.ingredienteToDto
import mappers.toIngredienteList
import models.Ingrediente
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import java.io.File
import java.nio.file.Files.createFile


object IngredienteXmlService: IngredientesStorageService {

    private val logger = KotlinLogging.logger {  }
    private val programPath = System.getProperty("user.dir")
    private val filePath = "$programPath${File.separator}data${File.separator}ingredientes.xml"

    private val serializer = Persister()


    override fun saveAll(items: List<Ingrediente>) {
        logger.debug("IngredienteXML: Guardando ingredientes en XML")
        val file = File(filePath)

        if (!file.exists()){
            createFile(file.toPath())
        }

        serializer.write(items.ingredienteToDto(), file)
        println(items)

    }

    override fun loadAll(): List<Ingrediente> {
        logger.debug("IngredienteXML: Cargando ingredientes desde el fichero XML")
        val file = File(filePath)

        val readingItems =  serializer.read(dto.IngredientesListDto:: class.java, file).toIngredienteList()
        readingItems.forEach { println(it) }
        return readingItems
    }


}