package service.storage.ingredientes.xml

import config.ConfigApp
import mappers.toDto
import mappers.toProductosList
import models.Ingrediente
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import service.storage.ingredientes.IngredientesStorageService
import java.io.File

private val logger = KotlinLogging.logger {}

object IngredientesFicheroXmlService : IngredientesStorageService {
    private val localFile =
        "${ConfigApp.INGREDIENTE_XML_PATH}${File.separator}" + "ingredientes.xml"

    private val serializer = Persister()

    override fun saveAll(items: List<Ingrediente>) {
        logger.info { "Guardando ingredientes en un fichero xml" }
        val file = File(localFile)
        serializer.write(items.toDto(), file)
    }

    override fun loadAll(): List<Ingrediente> {
        logger.info { "Cargando ingredientes desde un fichero de xml" }
        val file = File(localFile)
        return serializer.read(dto.IngredienteListDto::class.java, file).toProductosList()
    }
}