package services.storage.accidente

import config.AppConfig
import dto.AccidentesDto
import models.Accidente
import org.simpleframework.xml.core.Persister
import java.io.File
import java.time.LocalDateTime

object AccidenteFileXml: AccidenteStorageService{
    private val localFile = "${AppConfig.APP_DATA}${File.separator}accidente.xml"

    override fun saveAll(elements: List<Accidente>): List<Accidente> {
        val listDto = elements.map { it.toDto() }
        val accidentesDto = AccidentesDto(listDto)

        val serializer = Persister()
        serializer.write(accidentesDto, File(localFile))
        return elements
    }

    override fun loadAll(): List<Accidente> {
        val serializer = Persister()
        val accidentes = serializer
            .read(AccidentesDto::class.java, File(localFile))
        return accidentes.accidnetes.map { it.toClass() }
    }
}