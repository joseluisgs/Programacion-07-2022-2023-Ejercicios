package mappers

import dto.IngredienteDto
import locate.moneyToFloat
import locate.toLocalMoney
import models.Ingrediente

fun Ingrediente.toDto() = IngredienteDto(
    id = this.id.toString(),
    nombre = this.nombre,
    precio = this.precio.toLocalMoney()
)

fun IngredienteDto.toClass() = Ingrediente(
    id = this.id.toLong(),
    nombre = this.nombre,
    precio = this.precio.moneyToFloat()
)