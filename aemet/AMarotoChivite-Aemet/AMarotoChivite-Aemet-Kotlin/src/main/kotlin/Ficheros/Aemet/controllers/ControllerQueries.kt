package Ficheros.Aemet.controllers

import Ficheros.Aemet.models.Aemet
import mu.KotlinLogging
import java.time.LocalDate
import java.time.LocalTime
import kotlin.math.roundToInt

class ControllerQueries(private val listOfStorageReadFile: List<Aemet>) {

    private val logger = KotlinLogging.logger {}.debug { "ControllerQueries: CARGANDO CONSULTAS)" }

    // Agrupar por día y ubicación (utilizando pares)
    private val mapByDateAndLocate = listOfStorageReadFile.groupBy { Pair(it.date, it.nombrePoblacion) }

    // Agrupar por provincia (sin utilizar pares)
    private val mapByDateAndProvincia = listOfStorageReadFile.groupBy { listOf(it.date, it.nombreProvincia) }

    fun temperatureMaxPerDayAndLocate(): Map<Pair<LocalDate, String>, Double> {
        val mapByMaxTemperatures = mapByDateAndLocate.mapValues { (_, group) ->
            group.maxByOrNull { it.temperaturaMaxima }!!.temperaturaMaxima
        }
        return mapByMaxTemperatures
    }

    fun temperatureMinPerDayAndLocate(): Map<Pair<LocalDate, String>, Double> {
        val mapByMinTemperatures = mapByDateAndLocate.mapValues { (_, group) ->
            group.maxByOrNull { it.temperaturaMaxima }!!.temperaturaMinima
        }
        return mapByMinTemperatures
    }

    // Value: (día, lugar, valor y momento)
    fun temperatureMaxByProvincia(): Map<List<Any>, List<String>> {
        val mapByProvinciaWithTempMax = mapByDateAndProvincia.mapValues { (_, group) ->
            group.maxByOrNull { it.temperaturaMaxima }
        }

        return mapByProvinciaWithTempMax.mapValues { (_, aemet) ->
            if (aemet == null) {
                emptyList()
            } else {
                listOf(
                    aemet.date.toString(),
                    aemet.nombrePoblacion,
                    aemet.temperaturaMaxima.toString(),
                    aemet.horaTemperaturaMaxima.toString()
                )
            }
        }
    }

    // Value: (día, lugar, valor y momento)
    fun temperatureMinByProvincia(): Map<List<Any>, List<String>> {
        val mapByProvinciaWithTempMin = mapByDateAndProvincia.mapValues { (_, group) ->
            group.minByOrNull { it.temperaturaMinima }
        }

        return mapByProvinciaWithTempMin.mapValues { (_, aemet) ->
            if (aemet == null) {
                emptyList()
            } else {
                listOf(
                    aemet.date.toString(),
                    aemet.nombrePoblacion,
                    aemet.temperaturaMinima.toString(),
                    aemet.horaTemperaturaMinima.toString()
                )
            }
        }
    }

    // Value:  (día y valor)
    fun temperatureAverageByProvincia(): Map<List<Any>, List<String>> {

        // Calcular la temperatura media por provincia
        return mapByDateAndProvincia.mapValues { (_, group) ->
            group.map { (it.temperaturaMaxima + it.temperaturaMinima) / 2 }.average()
        } // Creamos el mapa final
            .mapValues { (_, value) ->
                listOf(value.toString())
            }
    }

    fun getListPrecipitacionAverageByDayAndProvincia(): List<String> {
        // Calculamos la precipitación media de cada grupo
        val averages = mapByDateAndProvincia.mapValues { (_, group) ->
            group.map { it.precipitacion }.average()
        }
        // Creamos una lista de strings con la información
        return averages.map { (key, value) ->
            "Día: ${key[0]}, Provincia: ${key[1]}, PrecipitaciónMedia: $value"
        }
    }

    fun numLocatesWasRainyByDayAndProvincia(): List<String> {
        // Calculamos el número de veces ha llovido por grupo
        val numRainy = mapByDateAndProvincia.mapValues { (_, group) ->
            group.count { it.precipitacion > 0 }
        }
        // Creamos una lista de strings con la información
        return numRainy.map { (key, value) ->
            "Día: ${key[0]}, Provincia: ${key[1]}, VecesLlovidoEnPoblaciones: $value"
        }
    }

    fun temperatureAverageFilterByOnePrivincia(provincia: String): List<String> {
        // Filtramos por la provincia deseada y únicamente disponemos de esa provincia agrupada con sus días
        val mapByOneProvincia = mapByDateAndProvincia.filter { it.key[1] == provincia }

        // Calculamos la temperatura media de cada grupo
        val averageTemperature = mapByOneProvincia.mapValues { (_, group) ->
            group.map { (it.temperaturaMaxima + it.temperaturaMinima) / 2 }.average().roundToInt()
        }

        // Creamos una lista de strings con la información
        return averageTemperature.map { (key, value) ->
            "Día: ${key[0]}, Provincia: ${key[1]}, TemperaturaMedia: ${value}ºC"
        }
    }

    fun temperatureMaxAverage(): List<String> {
        // Calculamos la temperatura media MÁXIMA de cada grupo
        val averageTemperature = mapByDateAndProvincia.mapValues { (_, group) ->
            group.map { it.temperaturaMaxima }.average().roundToInt()
        }

        // Creamos una lista de strings con la información
        return averageTemperature.map { (key, value) ->
            "Día: ${key[0]}, Provincia: ${key[1]}, TemperaturaMáximaMedia: ${value}ºC"
        }
    }

    fun temperatureMinAverage(): List<String> {
        // Calculamos la temperatura media MÍNIMA de cada grupo
        val averageTemperature = mapByDateAndProvincia.mapValues { (_, group) ->
            group.map { it.temperaturaMinima }.average().roundToInt()
        }

        // Creamos una lista de strings con la información
        return averageTemperature.map { (key, value) ->
            "Día: ${key[0]}, Provincia: ${key[1]}, TemperaturaMínimaMedia: ${value}ºC"
        }
    }

    // Antes de las 15:00 por día
    fun locatesWhereTempMaxWasBeforeAnHourByDay(): List<String> {
        val listFilterTime = listOfStorageReadFile.filter { aemet ->
            aemet.horaTemperaturaMaxima.isBefore(LocalTime.of(15, 0))
        }

        // Creamos una lista de strings con la información
        return listFilterTime.map {
            "Día: ${it.date}, Provincia: ${it.nombreProvincia}, HoraTemperaturaMax: ${it.horaTemperaturaMaxima}h"
        }
    }

    // Después de las 17:30 por día
    fun locatesWhereTempMinWasAfterAnHourByDay(): List<String> {
        val listFilterTime = listOfStorageReadFile.filter { aemet ->
            aemet.horaTemperaturaMinima.isAfter(LocalTime.of(17, 30))
        }

        // Creamos una lista de strings con la información
        return listFilterTime.map {
            "Día: ${it.date}, Provincia: ${it.nombreProvincia}, HoraTemperaturaMin: ${it.horaTemperaturaMinima}h"
        }
    }
}