package dto

import com.squareup.moshi.Json
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Json(name = "medicion")
@Root(name = "medicion")
data class MedicionDto (
    @field:Element(name = "poblacion")
    @param:Element(name = "poblacion")
    val poblacion: String,

    @field:Element(name = "provincia")
    @param:Element(name = "provincia")
    val provincia: String,

    @field:Element(name = "temperatura_maxima")
    @param:Element(name = "temperatura_maxima")
    val temperaturaMax: String,

    @field:Element(name = "hora_de_temperatura_maxima")
    @param:Element(name = "hora_de_temperatura_maxima")
    val horaTempMax: String,

    @field:Element(name = "temperatura_minima")
    @param:Element(name = "temperatura_minima")
    val temperaturaMin: String,

    @field:Element(name = "hora_de_temperatura_minima")
    @param:Element(name = "hora_de_temperatura_minima")
    val horaTempMin: String,

    @field:Element(name = "precipitacion")
    @param:Element(name = "precipitacion")
    val precipitacion: String,

    @field:Element(name = "dia")
    @param:Element(name = "dia")
    val dia: String
)