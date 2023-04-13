package services.storage.xml

import config.AppConfig
import models.Ingrediente
import models.dto.IngredientesDto
import models.dto.toDto
import models.dto.toIngredientes
import org.simpleframework.xml.core.Persister
import org.simpleframework.xml.stream.Format
import services.storage.IngredienteStorageService
import java.io.File

object IngredienteXmlService : IngredienteStorageService {
    private val localFile = "${AppConfig.XML_PATH}${File.separator}ingredientes.xml"

    override fun saveAll(items: List<Ingrediente>) {
        Persister(Format("""<?xml version="1.0" encoding="UTF-8"?>""")).write(items.toDto(), File(localFile))
    }

    override fun loadAll(): List<Ingrediente> {
        return Persister()
            .read(IngredientesDto::class.java, File(localFile))
            .toIngredientes()
    }
}