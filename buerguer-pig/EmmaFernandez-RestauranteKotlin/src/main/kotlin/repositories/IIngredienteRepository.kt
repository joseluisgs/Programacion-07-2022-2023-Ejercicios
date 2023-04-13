package repositories

import models.Ingrediente

interface IIngredienteRepository {
    fun crearIngrediente(ingrediente: Ingrediente): Ingrediente?
    fun listarIngredientes(): List<Ingrediente>
    fun listarIngredientePorID(id: Int): Ingrediente?
    fun guardarTodo()
    fun cargarTodo()
}