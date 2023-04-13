package services.storage.xml

import config.AppConfig
import models.Ingrediente
import org.simpleframework.xml.core.Persister
import org.simpleframework.xml.stream.Format
import services.storage.IngredienteStorageService
import java.io.File

object IngredienteXmlService : IngredienteStorageService {
    private val localFile = "${AppConfig.XML_PATH}${File.separator}ingredientes.xml"

    override fun saveAll(items: List<Ingrediente>) {
        Persister(Format("""<?xml version="1.0" encoding="UTF-8"?>""")).write(items, File(localFile))
    }

    override fun loadAll(): List<Ingrediente> {
        return Persister().read(List::class.java, File(localFile)) as List<Ingrediente>
    }
}