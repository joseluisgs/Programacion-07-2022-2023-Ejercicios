package services.storage.dupla

import config.AppConfig
import dto.DuplasDto
import models.Dupla
import org.simpleframework.xml.core.Persister
import java.io.File

object DuplaFileXml: DuplaStorageService {

    private val localFile = "${AppConfig.APP_DATA}${File.separator}duplas.xml"

    override fun saveAll(elements: List<Dupla>): List<Dupla> {
        val file = File(localFile)
        if (file.exists() && !file.canWrite()) return emptyList()

        val listDto = elements.map { it.toDto() }
        val accidentesDto = DuplasDto(listDto)

        val serializer = Persister()
        serializer.write(accidentesDto, file)
        return elements
    }

    override fun loadAll(): List<Dupla> {
        val file = File(localFile)
        if (!file.exists() || !file.canRead()) return emptyList()
        val serializer = Persister()
        val accidentes = serializer
            .read(DuplasDto::class.java, file)
        return accidentes.duplas.map { it.toClass() }
    }
}