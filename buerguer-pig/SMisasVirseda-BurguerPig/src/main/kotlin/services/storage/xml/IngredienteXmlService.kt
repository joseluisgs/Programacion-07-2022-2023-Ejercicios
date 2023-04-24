package services.storage.xml

import config.AppConfig
import dto.IngredientesDto
import dto.toDto
import dto.toIngredientes
import models.Ingrediente
import org.simpleframework.xml.core.Persister
import org.simpleframework.xml.stream.Format
import services.storage.IngredienteStorageService
import java.io.File

object IngredienteXmlService : IngredienteStorageService {
    private val path = AppConfig.xmlPath + File.separator + "Ingredientes.xml"
    override fun cargar(): List<Ingrediente> {
        return Persister().read(IngredientesDto::class.java, File(path)).toIngredientes()
    }

    override fun guardar(items: List<Ingrediente>) {
        Persister(Format("""<?xml version="1.0" encoding="UTF-8"?>""")).write(items.toDto(), File(path))
    }
}
