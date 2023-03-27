package models

import dto.InformeDto
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

data class Informe(
    val day: LocalDate,
    val temMedia: Double,
    val temMax: Triple<String, LocalTime, Double>,
    val temMin: Triple<String, LocalTime, Double>,
    val precipitacion: Pair<Boolean,Double>
): Serializable{
    fun toDto(): InformeDto {
        return InformeDto(
            day = day.toString(),
            temMedia = temMedia.toString(),

            temMax = temMax.third.toString(),
            poblacionMax = temMax.first,
            timeMax = temMax.second.toString(),

            temMin = temMin.third.toString(),
            poblacionMin = temMin.first,
            timeMin = temMin.second.toString(),

            isPrecipitacion = precipitacion.first.toString(),
            precipitacion = precipitacion.second.toString()
        )
    }
}