package Interfaz

import Models.Ingrediente

interface IngredienteRepository {
    fun crearIngrediente(ingrediente: Ingrediente)
    fun obtenerIngredientePorId(id: Int): Ingrediente?
    fun obtenerIngredienteMasCaro(): Ingrediente?
}