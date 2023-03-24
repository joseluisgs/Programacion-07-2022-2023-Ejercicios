package repositories

import models.Ingrediente
import services.storage.ingrediente.IngredienteStorageService

class IngredienteRepository(private val ingredientes: IngredienteStorageService) : IIngredienteRepository {
    private val ingredientesList = mutableListOf<Ingrediente>()

    override fun crearIngrediente(ingrediente: Ingrediente): Ingrediente? {
        return if (!ingredientesList.contains(ingrediente)) {
            ingredientesList.add(ingrediente)
            ingrediente
        } else null
    }

    override fun listarIngredientes(): List<Ingrediente> {
        return ingredientesList.toList()
    }

    override fun listarIngredientePorID(id: Int): Ingrediente? {
        return ingredientesList.find { it.id == id }
    }

    override fun guardarTodo() {
        ingredientes.saveAll(ingredientesList)
    }

    override fun cargarTodo() {
        ingredientesList.addAll(ingredientes.loadAll())
    }
}