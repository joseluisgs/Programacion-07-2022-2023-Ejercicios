package formateadores

import enums.TipoClima
import enums.TipoPersona
import enums.TipoSexo
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun String.toFechaYHora(hora: String): LocalDateTime {
    val fechaSplit = this.split("/").reversed().joinToString("-")
    val horaSplit = hora.split(":")
    val horaEdit =
        if (horaSplit[0].length == 1) {
            "0${horaSplit[0]}:${horaSplit[1]}:${horaSplit[2]}"
        } else {
            "${horaSplit[0]}:${horaSplit[1]}:${horaSplit[2]}"
        }
    return LocalDateTime.parse("${fechaSplit}T$horaEdit")
}

fun LocalTime.toFormatTime(): String{
    return this.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
}

fun String.toClima(): TipoClima {
    return when (this.lowercase()) {
        "despejado" -> TipoClima.DESPEJADO
        "lluvia débil" -> TipoClima.LLUVIA_DEBIL
        "nublado" -> TipoClima.NUBLADO
        "lluvia intensa" -> TipoClima.LLUVIA_INTENSA
        "nevando" -> TipoClima.NEVANDO
        else -> TipoClima.DESCONOCIDO // Null y desconocidos
    }
}

fun String.toPersona(): TipoPersona{
    return when (this.lowercase()) {
        "conductor" -> TipoPersona.CONDUCTOR
        "peatón" -> TipoPersona.PEATON
        "pasajero" -> TipoPersona.PASAJERO
        else -> TipoPersona.DESCONOCIDO // Null y desconocidos
    }
}

fun String.toSexo(): TipoSexo{
    return when (this.lowercase()) {
        "hombre" -> TipoSexo.HOMBRE
        "mujer" -> TipoSexo.MUJER
        else -> TipoSexo.DESCONOCIDO // Null y desconocidos
    }
}

fun String.toCoordenada(): Double?{
    return this
        .replace("\"", "")
        .replace(',', '.')
        .toDoubleOrNull()
}