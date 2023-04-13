package services.storage.informe

import config.AppConfig
import dto.InformeDto
import models.Informe
import java.io.File

object InformeFileCsv: InformeStorageService {
    private val localFile = "${AppConfig.APP_DATA}${File.separator}informe.csv"

    override fun saveAll(elements: List<Informe>): List<Informe> {
        val file = File(localFile)
        if (file.exists() && !file.canWrite()) return emptyList()
        file.writeText("day,temMedia,temMax,poblacionMax,timeMax,temMin,poblacionMin,timeMin,isPrecipitacion,precipitacion\n")
        elements.map { it.toDto() }.forEach{
            file.appendText(
                it.toCsvRow()
            )
        }
        return elements
    }

    override fun loadAll(): List<Informe> {
        val file = File(localFile)
        if (!file.exists() || !file.canRead()) return emptyList()
        return file.readLines().drop(1)
            .map { it.split(",") }
            .map { InformeDto(
                it[0],
                it[1],
                it[2],
                it[3],
                it[4],
                it[5],
                it[6],
                it[7],
                it[8],
                it[9],
            )}
            .map { it.toClass() }
    }
}