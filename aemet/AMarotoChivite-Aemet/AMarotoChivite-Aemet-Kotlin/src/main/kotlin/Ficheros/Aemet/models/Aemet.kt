package Ficheros.Aemet.models

import java.time.LocalDate
import java.time.LocalTime

class Aemet(
    val nombrePoblacion: String,
    val nombreProvincia: String,
    val temperaturaMaxima: Double,
    val horaTemperaturaMaxima: LocalTime,
    val temperaturaMinima: Double,
    val horaTemperaturaMinima: LocalTime,
    val precipitacion: Double,
    val date: LocalDate
) {
    override fun toString(): String {
        return "Aemet(nombrePoblacion='$nombrePoblacion', nombreProvincia='$nombreProvincia', temperaturaMaxima=$temperaturaMaxima, horaTemperaturaMaxima=$horaTemperaturaMaxima, temperaturaMinima=$temperaturaMinima, horaTemperaturaMinima=$horaTemperaturaMinima, precipitacion=$precipitacion, date=$date)"
    }
}