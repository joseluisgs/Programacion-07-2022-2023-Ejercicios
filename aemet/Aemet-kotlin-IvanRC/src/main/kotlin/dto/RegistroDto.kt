package dto

import com.squareup.moshi.Json
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Json(name = "registro")
@Root(name = "registro")
data class RegistroDto (
    @Json(name = "dia")
    @field:Element(name = "dia")
    @param:Element(name = "dia")
    val dia: String,

    @Json(name = "temperatura_max")
    @field:Element(name = "temperatura_max", required = false)
    @param:Element(name = "temperatura_max", required = false)
    val temperaturaMax: String? = null,

    @Json(name = "temperatura_min")
    @field:Element(name = "temperatura_min", required = false)
    @param:Element(name = "temperatura_min", required = false)
    val temperaturaMin: String? = null,

    @Json(name = "temperatura_media")
    @field:Element(name = "temperatura_media", required = false)
    @param:Element(name = "temperatura_media", required = false)
    val temperaturaMedia: String? = null,

    @Json(name = "lugar")
    @field:Element(name = "lugar", required = false)
    @param:Element(name = "lugar", required = false)
    val lugar: String? = null,

    @Json(name = "momento")
    @field:Element(name = "nombre", required = false)
    @param:Element(name = "nombre", required = false)
    val momento: String? = null,

    @Json(name = "hubo_precipitacion")
    @field:Element(name = "hubo_precipitacion", required = false)
    @param:Element(name = "hubo_precipitacion", required = false)
    val huboPrecipitacion: String? = null,

    @Json(name = "precipitacion")
    @field:Element(name = "precipitacion", required = false)
    @param:Element(name = "precipitacion", required = false)
    val precipitacion: String? = null
)