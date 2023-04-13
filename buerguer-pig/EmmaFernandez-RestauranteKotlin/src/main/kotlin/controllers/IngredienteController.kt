package controllers

import exceptions.IngredienteNoEncontradoException
import exceptions.IngredienteYaExisteException
import models.Ingrediente
import repositories.IIngredienteRepository

class IngredienteController(private val repository: IIngredienteRepository) {
    fun crearIngrediente(ingrediente: Ingrediente) =
        repository.crearIngrediente(ingrediente)
            ?: throw IngredienteYaExisteException("El ingrediente ya existe")

    fun listarIngredientePorID(id: Int) =
        repository.listarIngredientePorID(id)
            ?: throw IngredienteNoEncontradoException("No se encontró el ingrediente")

    fun listarIngredientes() = repository.listarIngredientes()
    fun guardarTodo() = repository.guardarTodo()
    fun cargarTodo() = repository.cargarTodo()
}