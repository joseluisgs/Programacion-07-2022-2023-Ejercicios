package dto

import models.Informe
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.time.LocalDate
import java.time.LocalTime

@Root(name = "informe")
data class InformeDto(
    @field:Attribute(name = "day")
    @param:Attribute(name = "day")
    val day: String,

    @field:Element(name = "temMedia")
    @param:Element(name = "temMedia")
    val temMedia: String,

    @field:Element(name = "temMax")
    @param:Element(name = "temMax")
    val temMax: String,
    @field:Element(name = "poblacionMax")
    @param:Element(name = "poblacionMax")
    val poblacionMax: String,
    @field:Element(name = "timeMax")
    @param:Element(name = "timeMax")
    val timeMax: String,

    @field:Element(name = "temMin")
    @param:Element(name = "temMin")
    val temMin: String,
    @field:Element(name = "poblacionMin")
    @param:Element(name = "poblacionMin")
    val poblacionMin: String,
    @field:Element(name = "timeMin")
    @param:Element(name = "timeMin")
    val timeMin: String,

    @field:Element(name = "isPrecipitacion")
    @param:Element(name = "isPrecipitacion")
    val isPrecipitacion: String,
    @field:Element(name = "precipitacion")
    @param:Element(name = "precipitacion")
    val precipitacion: String
){
    fun toClass(): Informe{
        return Informe(
            day = LocalDate.parse(day),
            temMedia = temMedia.toDouble(),
            temMax = Triple(poblacionMax, LocalTime.parse(timeMax), temMax.toDouble()),
            temMin = Triple(poblacionMin, LocalTime.parse(timeMin), temMin.toDouble()),
            precipitacion = Pair(isPrecipitacion.toBoolean(), precipitacion.toDouble())
        )
    }
}
