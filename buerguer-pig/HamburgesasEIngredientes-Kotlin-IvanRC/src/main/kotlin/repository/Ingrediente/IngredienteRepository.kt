package repository.Ingrediente

import model.Ingrediente
import repository.base.CrudRepository

interface IngredienteRepository: CrudRepository<Ingrediente, Int> {
    fun findByName(name: String): List<Ingrediente>
}