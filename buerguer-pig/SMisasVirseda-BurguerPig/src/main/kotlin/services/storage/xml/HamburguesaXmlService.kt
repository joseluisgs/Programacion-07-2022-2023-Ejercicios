package services.storage.xml

import config.AppConfig
import dto.HamburguesasDto
import dto.toDto
import dto.toHamburguesas
import models.Hamburguesa
import org.simpleframework.xml.core.Persister
import org.simpleframework.xml.stream.Format
import services.storage.HamburguesaStorageService
import java.io.File

object HamburguesaXmlService : HamburguesaStorageService {
    private val path = AppConfig.xmlPath + File.separator + "Hamburguesas.xml"
    override fun cargar(): List<Hamburguesa> {
        return Persister().read(HamburguesasDto::class.java, File(path)).toHamburguesas()
    }

    override fun guardar(items: List<Hamburguesa>) {
        Persister(Format("""<?xml version="1.0" encoding="UTF-8"?>""")).write(items.toDto(), File(path))
    }

}
