package services.storage.hamburguesas

import mappers.hamburguesaToDto
import mappers.toHamburguesaList
import models.Hamburguesa
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import java.io.File
import java.nio.file.Files

object HamburguesaXmlService: HamburguesaStorageService {
    private val logger = KotlinLogging.logger {  }
    private val programPath = System.getProperty("user.dir")
    private val filePath = "$programPath${File.separator}data${File.separator}hamburguesas.xml"

    private val serializer = Persister()



    override fun saveAll(items: List<Hamburguesa>) {
        logger.debug("HamburguesaXML: Guardando burguers en XML")
        val file = File(filePath)

        if (!file.exists()){
            Files.createFile(file.toPath())
        }
        serializer.write(items.hamburguesaToDto(), file)
        println(items)
    }

    override fun loadAll(): List<Hamburguesa> {
        logger.debug("HamburguesaXML: Cargando burguers de XML")
        val file = File(filePath)
        val readingFiles = serializer.read(dto.HamburguesasListDto::class.java, file).toHamburguesaList()
        readingFiles.forEach { println(it) }
        return readingFiles
    }
}