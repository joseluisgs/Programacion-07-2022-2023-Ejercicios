package utils

import java.time.LocalDate
import java.time.LocalTime


fun toLocalTime(input: String): LocalTime {
    val hora = input.split(":")
    return LocalTime.of(hora[0].toInt(), hora[1].toInt())
}

fun toLocalDate(input: String): LocalDate {
    var fecha= LocalDate.now()
    if (input.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}".toRegex())) {

        val partes = input.split("-")
        fecha = LocalDate.of(partes[2].toInt(), partes[1].toInt(), partes[0].toInt())
        return fecha

    } else if (input.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}".toRegex())) {

        val partes = input.split("/")
        fecha = LocalDate.of(partes[2].toInt(), partes[1].toInt(), partes[0].toInt())
        return fecha
    }
    return fecha
}


