package mappers

import dto.HamburguesaDto
import dto.HamburguesasListDto
import models.Hamburguesa
import java.util.*

fun Hamburguesa.toDto() = HamburguesaDto(
    id = this.id.toString(),
    name = this.name,
    receta = this.receta.map { it.toIngredienteDto() }.toList(),
)

fun HamburguesaDto.toHamburguesa() = Hamburguesa(
    id = UUID.fromString(id),
    name = this.name,
    receta = this.receta.map { it.toIngrediente() }.toMutableList()
)

fun List<Hamburguesa>.toDto() = HamburguesasListDto(
    hamburguesas = map { it.toDto() }
)

fun HamburguesasListDto.toHamburguesasList() = hamburguesas.map { it.toHamburguesa() }