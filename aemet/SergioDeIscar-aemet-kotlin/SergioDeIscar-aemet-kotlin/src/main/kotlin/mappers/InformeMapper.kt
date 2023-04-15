package mappers

import dto.InformeDto
import models.Informe
import java.time.LocalDate
import java.time.LocalTime

fun InformeDto.toClass(): Informe {
    return Informe(
        day = LocalDate.parse(day),
        temMedia = temMedia.toDouble(),
        temMax = Triple(poblacionMax, LocalTime.parse(timeMax), temMax.toDouble()),
        temMin = Triple(poblacionMin, LocalTime.parse(timeMin), temMin.toDouble()),
        precipitacion = Pair(isPrecipitacion.toBoolean(), precipitacion.toDouble())
    )
}

fun InformeDto.toCsvRow(): String{
    return "$day,$temMedia,$temMax,$poblacionMax,$timeMax,$temMin,$poblacionMin,$timeMin,$isPrecipitacion,$precipitacion\n"
}

fun Informe.toDto(): InformeDto {
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
