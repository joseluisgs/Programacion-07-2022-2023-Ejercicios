package mappers

import dto.IngredienteDto
import dto.IngredienteListDto
import models.Ingrediente

fun IngredienteDto.toIngrediente() = Ingrediente(
    id = this.id,
    name = this.name,
    price = this.price
)

fun Ingrediente.toIngredienteDto() = IngredienteDto(  // Importante que sea IngredienteDto
    id = this.id,
    name = this.name,
    price = this.price
)

fun List<Ingrediente>.toDto() = IngredienteListDto(
    ingredientes = map { it.toIngredienteDto() }
)

fun IngredienteListDto.toProductosList() = ingredientes.map { it.toIngrediente() }
