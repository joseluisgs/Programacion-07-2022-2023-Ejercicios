package models

import dto.DuplaDto
import formateadores.toFormatString
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

data class Dupla(
    val poblacion: String,
    val provincia: String,
    val temMax: Double,
    val timeMax: LocalTime,
    val temMin: Double,
    val timeMin: LocalTime,
    val precipitacion: Double,
    val day: LocalDate
): Serializable{
    fun toDto(): DuplaDto{
        return DuplaDto(
            poblacion = poblacion,
            provincia = provincia,
            temMax = temMax.toString(),
            timeMax = timeMax.toFormatString(),
            temMin = temMin.toString(),
            timeMin = timeMin.toFormatString(),
            precipitacion = precipitacion.toString(),
            day = day.toString()
        )
    }
}
