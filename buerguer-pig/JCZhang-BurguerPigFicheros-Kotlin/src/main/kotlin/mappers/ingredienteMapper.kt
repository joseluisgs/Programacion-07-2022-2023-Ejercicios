package mappers

import dto.IngredienteDTO
import dto.IngredientesListDto
import models.Ingrediente



fun IngredienteDTO.toIngrediente() = Ingrediente(
    ID = this.id,
    nombre = this.nombre,
    precio = this.precio
)

fun Ingrediente.toDto() = IngredienteDTO(
    id = ID,
    nombre = nombre,
    precio = precio

)

fun List<Ingrediente>.ingredienteToDto() = IngredientesListDto(
    ingredientes = map{it.toDto()}
)

fun IngredientesListDto.toIngredienteList() = ingredientes.map { it.toIngrediente() }