package utils

import java.time.LocalDate
import java.time.LocalTime

fun toLocalTime(hora: String): LocalTime {
    val horas = hora.split(":").map { it.toInt() }.toMutableList()
    // Esto lo pongo debido a que una hora es -> 04:30, en ese caso supuse que se saltaron los segundos ya que eran 0
    if(horas.size == 2){
        horas.add(0)
    }
    return LocalTime.of(horas[0], horas[1], horas[2])
}

fun `toLocalDateDD-MM-YYYY`(fecha: String): LocalDate {
    var fechas = IntArray(3)
    var fechaFinal = LocalDate.now()
    if(fecha.matches(Regex("[0-9]{2}/[0-9]{2}/[0-9]{4}"))) {
        fechas = fecha.split("/").map { it.trim() }.map { it.toInt() }.toIntArray()
        fechaFinal = LocalDate.of(fechas[2], fechas[1], fechas[0])
    }else{
        if(fecha.matches(Regex("[0-9]{2}-[0-9]{2}-[0-9]{4}"))){
            fechas = fecha.split("-").map { it.trim() }.map { it.toInt() }.toIntArray()
            fechaFinal = LocalDate.of(fechas[0], fechas[1], fechas[2])
        }
    }
    return fechaFinal
}