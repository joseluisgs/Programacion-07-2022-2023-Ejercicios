package dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ingrediente")
data class IngredienteDto(
    @field:Attribute(name = "id")
    @param:Attribute(name = "id")
    val id: Int,    // importante las comas

    @field:Element(name = "name")
    @param:Element(name = "name")
    val name: String,

    @field:Element(name = "price")
    @param:Element(name = "price")
    val price: Double,
)

@Root(name = "ingredientes")
data class IngredienteListDto(
    @field:ElementList(name = "productos", inline = true)
    @param:ElementList(name = "productos", inline = true)
    val ingredientes: List<IngredienteDto>
)