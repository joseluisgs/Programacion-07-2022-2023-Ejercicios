package controller

import exceptions.IngredienteNoEncontrado
import factories.restablecerFactory
import models.Ingrediente
import mu.KotlinLogging
import repositories.ingredientes.IngredientesRepository
import validators.validate

private val logger = KotlinLogging.logger {}

class IngredientesController(
    private val ingredientesRepository: IngredientesRepository
) {
    fun findAll(): List<Ingrediente> {
        logger.debug { "Buscando todos los ingredientes" }
        return ingredientesRepository.findAll()
    }

    fun save(entity: Ingrediente): Ingrediente {
        logger.debug { "Guardando Ingrediente en el repository" }
        entity.validate()
        return ingredientesRepository.save(entity)
    }

    fun findById(id: Int): Ingrediente {
        logger.debug { "Buscando por Id un Ingrediente en el repository" }
        return ingredientesRepository.findById(id)
            ?: throw IngredienteNoEncontrado("Ingrediente con la siguiente $id no ha sido encontrado")
    }

    fun clear() {
        logger.debug { "Borrando almacén desde el controller de ingredientes y reseteando el contador del factory" }
        restablecerFactory()
        return ingredientesRepository.clear()
    }

    // Precio medio de los ingredientes
    fun precioMedioIngredientes(): Double {
        return ingredientesRepository.midPriceIngredientes()
    }
}