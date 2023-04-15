package formateadores

import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun LocalTime.toFormatString(): String{
    return this.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
}

fun String.toLocalTimeFormate(): LocalTime{
    val split = this.split(":")
    return LocalTime.of(split[0].toInt(), split[1].toInt(), 0)
}