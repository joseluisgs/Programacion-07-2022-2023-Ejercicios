package models.dto

import models.Hamburguesa
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "hamburguesas")
data class HamburguesasDto(
    @field:ElementList(name = "hamburguesas", inline = true)
    @param:ElementList(name = "hamburguesas", inline = true)
    val hamburguesas: List<HamburguesaDto>
)

fun List<Hamburguesa>.toDto() = HamburguesasDto(hamburguesas = map { it.toDto() })
fun HamburguesasDto.toHamburguesas() = hamburguesas.map { it.toHamburguesa() }