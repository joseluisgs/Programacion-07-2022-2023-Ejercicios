package controllers

import mu.KotlinLogging
import repositories.Ingredientes.IngredientesRepository

class IngredientesController(private val ingredientesRepository: IngredientesRepository) {

    private val logger = KotlinLogging.logger {  }
    fun save(){
        logger.debug("IngredientesController: save()")
        ingredientesRepository.save()
    }

    fun load(){
        logger.debug("IngredientesController: load()")
        ingredientesRepository.load()
    }
}