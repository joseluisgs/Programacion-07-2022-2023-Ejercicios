package mapper

import dto.ingrediente.IngredienteDto
import dto.ingrediente.ListaIngredientesDto
import model.Ingrediente

fun Ingrediente.toDto(): IngredienteDto{
    return IngredienteDto(
        id = this.id.toString().trim(),
        nombre = this.nombre.trim(),
        precio = this.precio.toString().trim()
    )
}

fun IngredienteDto.toIngrediente(): Ingrediente{
    return Ingrediente(
        id = this.id.toInt(),
        nombre = this.nombre,
        precio = this.precio.toDouble()
    )
}

fun List<Ingrediente>.toDto(): ListaIngredientesDto{
    return ListaIngredientesDto(map { it.toDto() })
}

fun ListaIngredientesDto.toIngrediente(): List<Ingrediente>{
    return ingredientesDto.map { it.toIngrediente() }
}