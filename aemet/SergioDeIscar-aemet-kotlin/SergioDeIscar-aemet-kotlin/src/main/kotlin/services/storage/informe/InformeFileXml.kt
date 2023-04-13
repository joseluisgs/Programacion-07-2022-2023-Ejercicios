package services.storage.informe

import config.AppConfig
import dto.InformesDto
import models.Informe
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import java.io.File

private val logger = KotlinLogging.logger {  }

object InformeFileXml: InformeStorageService {
    private val localFile = "${AppConfig.APP_DATA}${File.separator}informe.xml"

    override fun saveAll(elements: List<Informe>): List<Informe> {
        logger.debug { "InformeFileXml ->\tInformeFileXml" }
        val file = File(localFile)
        if (file.exists() && !file.canWrite()) return emptyList()

        val serializer = Persister()
        serializer.write(
            InformesDto(
                elements.map { it.toDto() }
            ),
            file
        )

        return elements
    }

    override fun loadAll(): List<Informe> {
        logger.debug { "InformeFileXml ->\tloadAll" }

        val file = File(localFile)
        if (!file.exists() || !file.canRead()) return emptyList()

        val serializer = Persister()

        return serializer.read(InformesDto::class.java, file).informes.map { it.toClass() }
    }
}