package dto

import com.squareup.moshi.Json
import models.Hamburguesa
import models.Ingrediente
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
    val ingredientes: String,

    @field:Element(name = "precio")
    @param:Element(name = "precio")
    @Json(name = "precio")
    val precio: String
)

fun Hamburguesa.toDto() = HamburguesaDto(
    this.id.toString(),
    this.nombre,
    this.ingredientes.joinToString(":") { it.toString() },
    this.precio.toString()
)

fun HamburguesaDto.toHamburguesa() = Hamburguesa(
    UUID.fromString(this.id),
    this.nombre,
    this.ingredientes.split(":").map { ingrediente ->
        val (id, nombre, precio) = ingrediente.split(";")
        Ingrediente(
            id = id.toInt(),
            nombre = nombre,
            precio = precio.toDouble()
        )
    }
)
