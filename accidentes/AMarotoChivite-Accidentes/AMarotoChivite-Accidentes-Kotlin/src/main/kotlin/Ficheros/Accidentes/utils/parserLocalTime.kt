package Ficheros.Accidentes.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

fun parseFecha(fechaStr: String): LocalDate {
    return LocalDate.parse(fechaStr, formatter)
}