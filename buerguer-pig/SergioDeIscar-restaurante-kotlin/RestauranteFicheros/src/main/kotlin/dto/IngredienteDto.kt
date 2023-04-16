package dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "ingrediente")
data class IngredienteDto(
    @field:Attribute(name = "id")
    @param:Attribute(name = "id")
    val id: String,
    @field:Attribute(name = "nombre")
    @param:Attribute(name = "nombre")
    val nombre: String,
    @field:Attribute(name = "precio")
    @param:Attribute(name = "precio")
    val precio: String
)