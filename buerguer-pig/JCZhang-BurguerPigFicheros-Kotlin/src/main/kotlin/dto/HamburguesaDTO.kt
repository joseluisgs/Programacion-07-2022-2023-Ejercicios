package dto

import models.Ingrediente
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


@Root(name = "hamburguesa")
data class HamburguesaDTO(
    @field: Attribute(name = "id")
    @param: Attribute(name = "id")
    val id: String,

    @field: Element(name = "nombre")
    @param: Element(name = "nombre")
    val nombre: String,

    @field: ElementList(name = "ingredientes", inline = true, required= false)
    @param: ElementList(name = "ingredientes", inline = true, required =  false)
    val ingredientes: List<Ingrediente>,

    @field: Attribute(name = "precio")
    @param: Attribute(name = "precio")
    val precio: Double

)

@Root(name = "hamburguesas")
data class HamburguesasListDto(
    @field: ElementList(name = "hamburguesa", inline = true)
    @param: ElementList(name = "hamburguesa", inline = true)
    val hamburguesas: List<HamburguesaDTO>
)


