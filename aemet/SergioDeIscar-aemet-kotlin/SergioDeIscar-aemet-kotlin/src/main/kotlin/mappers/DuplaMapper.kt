package mappers

import dto.DuplaDto
import formateadores.toFormatString
import formateadores.toLocalTimeFormate
import models.Dupla
import java.time.LocalDate

fun Dupla.toDto(): DuplaDto {
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

fun DuplaDto.toClass(): Dupla {
    return Dupla(
        poblacion,
        provincia,
        temMax.toDouble(),
        timeMax.toLocalTimeFormate(),
        temMin.toDouble(),
        timeMin.toLocalTimeFormate(),
        precipitacion.toDouble(),
        LocalDate.parse(day)
    )
}