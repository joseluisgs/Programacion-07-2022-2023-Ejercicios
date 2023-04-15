package services.storage.dupla

import formateadores.toLocalTimeFormate
import models.Dupla
import mu.KotlinLogging
import java.lang.RuntimeException
import java.time.LocalDate

private val logger = KotlinLogging.logger {}

object DuplaFileCsv: DuplaStorageService {
    override fun saveAll(elements: List<Dupla>): List<Dupla> {
        logger.debug { "AccidenteFileCsv ->\tsaveAll" }
        // Por ahora no se va a guardar nada en el fichero
        return elements
    }

    override fun loadAll(): List<Dupla> {
        logger.debug { "DuplaFileCsv ->\tloadAll" }
        return loadOneFile("Aemet20171029") + loadOneFile("Aemet20171030") + loadOneFile("Aemet20171031")
    }

    private fun loadOneFile(name:String): Iterable<Dupla>{
        val duplasCsv = DuplaFileCsv::class.java.getResourceAsStream("/$name.csv")
            ?: throw RuntimeException("Error al cargar el CSV o el fichero no existe")
        val duplas = mutableListOf<Dupla>()

        val date = LocalDate.parse(
            "${name.substring(5, 9)}-${name.substring(9, 11)}-${name.substring(11, 13)}"
        ) ?: throw RuntimeException("Error al cargar el CSV o el fichero no existe")

        println()

        duplasCsv.bufferedReader().forEachLine {
            val row = it.split(";").toMutableList()
            duplas.add(
                Dupla(
                    poblacion = row[0],
                    provincia = row[1],
                    temMax = row[2].toDouble(),
                    timeMax = row[3].toLocalTimeFormate(),
                    temMin = row[4].toDouble(),
                    timeMin = row[5].toLocalTimeFormate(),
                    precipitacion = row[6].toDouble(),
                    day = date
                )
            )
        }

        return duplas.toList()
    }
}