package Ficheros.Accidentes.controllers

import Ficheros.Accidentes.models.dto.AccidenteDTO
import Ficheros.Accidentes.utils.parseFecha
import mu.KotlinLogging
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class ControllerQueries(private val listOfStorageReadFile: List<AccidenteDTO>) {

    private val logger = KotlinLogging.logger {}.debug { "ControllerQueries: CARGANDO CONSULTAS)" }

    private val mapByDistrito = listOfStorageReadFile.groupBy { it.distrito }

    fun getAccidentesOnlyAlcoholOrDrugs(): List<AccidenteDTO> {
        return listOfStorageReadFile.filter { it.positivaDroga != "NULL" || it.positivaAlcohol != "N" }
    }

    fun numAccidentesOnlyAlcoholAndDrugs(): Int {
        return listOfStorageReadFile.count { it.positivaDroga != "NULL" && it.positivaAlcohol != "N" }
    }

    fun accidentesBySexo(): Map<String, List<AccidenteDTO>> {
        return listOfStorageReadFile.groupBy { it.sexo }
    }

    fun accidentesByMonths(): Map<String, List<AccidenteDTO>> {
        return listOfStorageReadFile.groupBy { it.fecha.substring(3, 5) }
    }

    fun accidentesByVehiculos(): Map<String, List<AccidenteDTO>> {
        return listOfStorageReadFile.groupBy { it.tipoVehiculo }
    }

    // EN LEGANÉS
    fun accidentesByLocalizacion(localizacion: String): List<AccidenteDTO> {
        return listOfStorageReadFile.filter { it.localizacion.contains(localizacion.uppercase(Locale.getDefault())) }
    }

    fun numAccidentesByDistrito(): Map<String, Int> {
        return mapByDistrito.mapValues { (_, list) -> list.size }
    }

    fun estadisticasByDistrito(): List<AccidenteDTO> {
        TODO()
    }

    fun accidentesByDistritoOrderDesc(numeroAccidentersParaVisualizar: Int): Map<String, List<AccidenteDTO>> {
        return mapByDistrito.mapValues { (_, group) ->
            group.take(numeroAccidentersParaVisualizar)
        }.toSortedMap(Comparator.reverseOrder())
    }

    // (a partir de las 8 de la tarde hasta las 6 de la mañana)
    fun accidentesEnFinDeSemanaAndNoche(): List<AccidenteDTO> {
        return listOfStorageReadFile.filter { accidente ->
            val fecha = parseFecha(accidente.fecha)
            val esFinDeSemana = fecha.dayOfWeek == DayOfWeek.SATURDAY || fecha.dayOfWeek == DayOfWeek.SUNDAY
            // No lo puede parsear! Por ello de manera opcional acepta "h:mm" como "HH:mm"
            val flexibleFormatter = DateTimeFormatter.ofPattern("[H:]mm[:ss][ a]")
            val hora = LocalTime.parse(accidente.hora, flexibleFormatter)

            esFinDeSemana && (hora >= LocalTime.of(20, 0) || hora < LocalTime.of(6, 0))
        }
    }

    fun accidentesEnFinDeSemanaAndNocheAndPositiveAlcohol(): List<AccidenteDTO> {
        val listFinDeSemanaNoche = accidentesEnFinDeSemanaAndNoche()

        return listFinDeSemanaNoche.filter {
            it.positivaDroga != "NULL" && it.positivaAlcohol != "N"
        }
    }

    fun accidentesWhereDead(): List<AccidenteDTO> {
        return listOfStorageReadFile.filter { it.lesividad.contains("Fallecido") }
    }

    // Comprobar si el distrito con más accidentes coincide con el distrito donde hay más accidentes en fin de semana.
    fun isDistritoMoreRiskySameAsDistritoMoreRiskyInWeekend(): Triple<Boolean, String, String> {
        val numAccidentesByDistrito = numAccidentesByDistrito()
        val distritoMoreAccidentes = numAccidentesByDistrito.maxByOrNull { it.value }!!.key

        val distritoMoreAccidentesWeekend = mapByDistrito.mapValues {
            it.value.filter { accidente ->
                parseFecha(accidente.fecha).dayOfWeek == DayOfWeek.SATURDAY
                        || parseFecha(accidente.fecha).dayOfWeek == DayOfWeek.SUNDAY
            }
        }.mapValues { (_, list) -> list.size }
            .maxByOrNull { it.value }!!.key

        if (distritoMoreAccidentes == distritoMoreAccidentesWeekend) {
            return Triple(true, distritoMoreAccidentes, distritoMoreAccidentesWeekend)
        } else {
            return Triple(false, distritoMoreAccidentes, distritoMoreAccidentesWeekend)
        }
    }

    fun numAccidentesWherePositiveAlcholoOrDrugsAndDeads(): Int {
        return listOfStorageReadFile.count {
            (it.positivaDroga != "NULL" || it.positivaAlcohol != "N") && it.lesividad.contains("Fallecido")
        }
    }

    fun numAccidentesWhereHitPeople(): List<AccidenteDTO> {
        return listOfStorageReadFile.filter { it.tipoAccidente.contains("persona") }
    }

    fun accidentesByMeteorologia(): Map<String, List<AccidenteDTO>> {
        return listOfStorageReadFile.groupBy { it.estadoMeteorologico }
    }

    fun accidentesWhereHitAnimal(): List<AccidenteDTO> {
        return listOfStorageReadFile.filter { it.tipoAccidente.contains("animal") }
    }

}