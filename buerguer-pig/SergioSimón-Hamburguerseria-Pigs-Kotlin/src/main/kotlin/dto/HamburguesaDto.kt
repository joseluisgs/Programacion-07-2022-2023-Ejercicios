package dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "Hamburguesa")
data class HamburguesaDto(
    // Para evitar los problemas de los tipos usamos un DTO con los tipos String
    // Field es para poder escribir y param para crear el constructor
    @field:Attribute(name = "id")
    @param:Attribute(name = "id")
    val id: String,     // importante las comas

    @field:Attribute(name = "name") // Cambia el nombre del elemento
    @param:Attribute(name = "name") // Cambia el nombre del elemento
    val name: String,

    @field:ElementList(name = "receta", inline = true) // inline para que no se cree un elemento contenedor
    @param:ElementList(name = "receta", inline = true) // inline para que no se cree un elemento contenedor
    val receta: List<IngredienteDto>
)

@Root(name = "List_Hamburguesas")
data class HamburguesasListDto(
    @field:ElementList(name = "List_Hamburguesas", inline = true)
    @param:ElementList(name = "List_Hamburguesas", inline = true)
    val listHamburguesas: List<HamburguesaDto>
)

// @Root(name = "Hamburguesas")
// data class HamburguesasListDto(
//     @field:ElementList(name = "hamburguesas", inline = true)
//     @param:ElementList(name = "hamburguesas", inline = true)
//     val hamburguesas: List<HamburguesaDto>
// )