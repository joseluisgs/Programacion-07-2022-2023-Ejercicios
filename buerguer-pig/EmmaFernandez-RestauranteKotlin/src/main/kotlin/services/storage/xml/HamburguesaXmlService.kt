package services.storage.xml

import config.AppConfig
import models.Hamburguesa
import models.dto.HamburguesasDto
import models.dto.toDto
import models.dto.toHamburguesas
import org.simpleframework.xml.core.Persister
import org.simpleframework.xml.stream.Format
import services.storage.HamburguesaStorageService
import java.io.File

object HamburguesaXmlService : HamburguesaStorageService {
    private val localFile = "${AppConfig.XML_PATH}${File.separator}hamburguesas.xml"

    override fun saveAll(items: List<Hamburguesa>) {
        Persister(Format("""<?xml version="1.0" encoding="UTF-8"?>""")).write(items.toDto(), File(localFile))
    }

    override fun loadAll(): List<Hamburguesa> {
        return Persister()
            .read(HamburguesasDto::class.java, File(localFile))
            .toHamburguesas()
    }
}