package models.dto

import com.squareup.moshi.Json
import models.Hamburguesa
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.util.*

@Root(name = "hamburguesa")
data class HamburguesaDto(
    @field:Attribute(name = "id")
    @param:Attribute(name = "id")
    @Json(name = "id")
    val id: String,

    @field:Element(name = "nombre")
    @param:Element(name = "nombre")
    @Json(name = "nombre")
    val nombre: String,

    @field:Element(name = "ingredientes")
    @param:Element(name = "ingredientes")
    @Json(name = "ingredientes")
    val ingredientes: IngredientesDto
)

fun Hamburguesa.toDto() = HamburguesaDto(
    id = this.id.toString(),
    nombre = this.nombre,
    ingredientes = this.ingredientes.toDto()
)

fun HamburguesaDto.toHamburguesa() = Hamburguesa(
    id = UUID.fromString(this.id),
    nombre = this.nombre,
    ingredientes = this.ingredientes.toIngredientes()
)