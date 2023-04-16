package dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "producto")
data class ProductoDto(
    @field:Attribute(name = "id")
    @param:Attribute(name = "id")
    val id: String,

    @field:Element(name = "nombre")
    @param:Element(name = "nombre")
    val nombre: String,

    @field:Element(name = "precio")
    @param:Element(name = "precio")
    val precio: String,

    @field:Attribute(name = "tipo")
    @param:Attribute(name = "tipo")
    val tipo: String,

    @field:ElementList(name = "ingredientes", required = false)
    @param:ElementList(name = "ingredientes", required = false)
    val ingredientes: List<IngredienteDto>? = null,

    @field:Element(name = "capacidad", required = false)
    @param:Element(name = "capacidad", required = false)
    val capacidad: String? = null
)