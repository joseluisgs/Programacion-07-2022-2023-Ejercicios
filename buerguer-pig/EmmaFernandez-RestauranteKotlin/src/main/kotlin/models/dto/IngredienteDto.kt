package models.dto

import com.squareup.moshi.Json
import models.Ingrediente
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "ingrediente")
data class IngredienteDto(
    @field:Attribute(name = "id")
    @param:Attribute(name = "id")
    @Json(name = "id")
    val id: String,

    @field:Element(name = "nombre")
    @param:Element(name = "nombre")
    @Json(name = "nombre")
    val nombre: String,

    @field:Element(name = "precio")
    @param:Element(name = "precio")
    @Json(name = "precio")
    val precio: String
)

fun Ingrediente.toDto() = IngredienteDto(
    id = this.id.toString(),
    nombre = this.nombre,
    precio = this.precio.toString()
)

fun IngredienteDto.toIngrediente() = Ingrediente(
    id = id.toInt(),
    nombre = nombre,
    precio = precio.toDouble()
)