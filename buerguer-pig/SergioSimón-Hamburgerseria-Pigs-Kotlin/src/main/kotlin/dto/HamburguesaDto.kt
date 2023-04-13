package dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "Hamburguesa")
data class HamburguesaDto(
    @field:Attribute(name = "id")
    @param:Attribute(name = "id")
    val id: String,

    @field:Attribute(name = "name")
    @param:Attribute(name = "name")
    val name: String,

    @field:ElementList(name = "receta", inline = true)
    @param:ElementList(name = "receta", inline = true)
    val receta: List<IngredienteDto>
)

@Root(name = "Hamburguesas")
data class HamburguesasListDto(
    @field:ElementList(name = "hamburguesas", inline = true)
    @param:ElementList(name = "hamburguesas", inline = true)
    val hamburguesas: List<HamburguesaDto>
)