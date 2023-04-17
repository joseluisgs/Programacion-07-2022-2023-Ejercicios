package dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ingrediente")
data class IngredienteDto(
    // Para evitar los problemas de los tipos usamos un DTO con los tipos String
    // Field es para poder escribir y param para crear el constructor
    @field:Attribute(name = "id")
    @param:Attribute(name = "id")
    val id: Int,    // No olvides las comas

    @field:Element(name = "name") // Cambia el nombre del elemento
    @param:Element(name = "name") // Cambia el nombre del elemento
    val name: String,

    @field:Element(name = "price")
    @param:Element(name = "price")
    val price: Double,
)

@Root(name = "List_Ingredientes")
data class IngredienteListDto(
    @field:ElementList(name = "List_Ingredientes", inline = true)
    @param:ElementList(name = "List_Ingredientes", inline = true)
    val listIngredientes: List<IngredienteDto>
)

// @Root(name = "ingredientes")
// data class IngredienteListDto(
//     @field:ElementList(name = "productos", inline = true)
//     @param:ElementList(name = "productos", inline = true)
//     val ingredientes: List<IngredienteDto>
// )