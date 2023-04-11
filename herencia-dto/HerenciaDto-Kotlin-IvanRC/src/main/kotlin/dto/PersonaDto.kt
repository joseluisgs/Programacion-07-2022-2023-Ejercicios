package dto

import com.squareup.moshi.Json
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Json(name = "persona")
@Root(name = "persona")
data class PersonaDto(
    @Json(name = "nombre")
    @field:Element(name = "nombre")
    @param:Element(name = "nombre")
    val nombre: String,

    @Json(name = "edad")
    @field:Element(name = "edad", required = false)
    @param:Element(name = "edad", required = false)
    val edad: String? = null,

    @Json(name = "modulo")
    @field:Element(name = "modulo", required = false)
    @param:Element(name = "modulo", required = false)
    val modulo: String? = null,

    @Json(name = "tipo")
    @field:Element(name = "tipo")
    @param:Element(name = "tipo")
    val tipo: String
)