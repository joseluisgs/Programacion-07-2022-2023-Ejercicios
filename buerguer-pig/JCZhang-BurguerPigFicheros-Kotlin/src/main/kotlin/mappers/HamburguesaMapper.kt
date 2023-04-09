package mappers

import dto.HamburguesaDTO
import dto.HamburguesasListDto
import models.Hamburguesa
import java.util.*


fun Hamburguesa.toDto() = HamburguesaDTO(
    id = this.id.toString() ,
    nombre = this.nombre,
    ingredientes = this.ingredientes,
    precio = this.precio
)

fun HamburguesaDTO.toHamburguesa() = Hamburguesa(
    id = UUID.fromString(id),
    nombre = nombre,
    ingredientes = ingredientes,
    precio = precio
)


fun List<Hamburguesa>.hamburguesaToDto() = HamburguesasListDto(
    hamburguesas = map { it.toDto() }
)


fun  HamburguesasListDto.toHamburguesaList() = hamburguesas.map { it.toHamburguesa() }
