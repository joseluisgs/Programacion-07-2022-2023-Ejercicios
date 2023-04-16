package Ficheros.BurguerPig.repositories

import Ficheros.BurguerPig.models.Ingredient

class RepositoryIngredient {

    private val repository = mutableListOf<Ingredient>()

    // Agrego ingredientes en el repository sin límite de stock, para ahorrarnos introducirlos desde el Main
    // a través del controlador
    init {
        repository.add(Ingredient("Tomate", 0.4))
        repository.add(Ingredient("Lechuga", 0.2))
        repository.add(Ingredient("Cerdo", 2.2))
        repository.add(Ingredient("Pollo", 1.5))
        repository.add(Ingredient("Atún", 2.1))
        repository.add(Ingredient("Mostaza", 0.5))
        repository.add(Ingredient("Ketchup", 0.5))
        repository.add(Ingredient("Queso", 1.3))
    }
    
    fun getAllIngredient(): List<Ingredient> {
        return repository.toList()
    }

}