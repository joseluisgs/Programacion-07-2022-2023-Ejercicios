package utils

import validator.validate
import java.time.LocalDate
import java.time.LocalTime

fun String.toLocalTime(): LocalTime{
    //Asumo que en caso de que falten valores, faltan en el siguiente orden: segundos -> minutos -> horas
    val hora = this.split(":").map { it.toInt() }
    return if(hora.size == 3){
        LocalTime.of(hora[0], hora[1], hora[2])
    }else{
        if(hora.size == 2){
            LocalTime.of(hora[0], hora[1], 0)
        }else{
            LocalTime.of(hora[0], 0, 0)
        }
    }
}

fun String.toLocalDate(): LocalDate{
    val fecha = this.split("-").map { it.toInt() }
    return LocalDate.of(
        fecha[0], fecha[1], fecha[2]
    )
}

fun String.toFormatoFechaValido(): Triple<Int, Int, Int>{
    this.validate()
    val fecha = this.removePrefix("Aemet").removeSuffix(".csv")
    return Triple(fecha.substring(0,4).toInt(), fecha.substring(4,6).toInt(), fecha.substring(6,8).toInt())
}