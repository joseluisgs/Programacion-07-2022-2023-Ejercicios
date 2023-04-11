package service.storage.hamburguesas.xml

import config.ConfigApp
import mappers.toDto
import mappers.toHamburguesasList
import models.Hamburguesa
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import service.storage.hamburguesas.HamburguesasStorageService
import java.io.File

private val logger = KotlinLogging.logger {}

object hamburguesasFicheroXmlService : HamburguesasStorageService {

    private val localFile =
        "${ConfigApp.APP_DATA}${File.separator}" + "hamburgesa" + File.separator + "xml" + File.separator + "hamburgesa.xml"

    private val serializer = Persister()

    override fun saveAll(items: List<Hamburguesa>) {
        logger.info { "Guardando hamburguesas en un fichero de xml" }
        val file = File(localFile)
        serializer.write(items.toDto(), file)
    }

    override fun loadAll(): List<Hamburguesa> {
        logger.info { "Cargando hamburguesas desde un fichero de xml" }
        val file = File(localFile)
        return serializer.read(dto.HamburguesasListDto::class.java, file).toHamburguesasList()
    }
}