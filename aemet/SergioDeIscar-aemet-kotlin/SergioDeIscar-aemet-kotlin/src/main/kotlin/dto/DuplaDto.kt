package dto

import formateadores.toLocalTimeFormate
import models.Dupla
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.time.LocalDate

@Root(name = "dupla")
data class DuplaDto(
    @field:Attribute(name = "poblacion")
    @param:Attribute(name = "poblacion")
    val poblacion: String,
    @field:Attribute(name = "provincia")
    @param:Attribute(name = "provincia")
    val provincia: String,
    @field:Element(name = "temMax")
    @param:Element(name = "temMax")
    val temMax: String,
    @field:Element(name = "timeMax")
    @param:Element(name = "timeMax")
    val timeMax: String,
    @field:Element(name = "temMin")
    @param:Element(name = "temMin")
    val temMin: String,
    @field:Element(name = "timeMin")
    @param:Element(name = "timeMin")
    val timeMin: String,
    @field:Element(name = "precipitacion")
    @param:Element(name = "precipitacion")
    val precipitacion: String,
    @field:Attribute(name = "day")
    @param:Attribute(name = "day")
    val day: String
)
