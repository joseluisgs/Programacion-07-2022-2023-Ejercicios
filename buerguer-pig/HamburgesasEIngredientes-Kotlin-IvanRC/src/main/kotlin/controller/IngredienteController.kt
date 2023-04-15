package controller

import exception.ingredienteException.IngredienteAlreadyExistsException
import exception.ingredienteException.IngredienteBadRequestException
import exception.ingredienteException.IngredienteNotFoundException
import model.Ingrediente
import mu.KotlinLogging
import repository.Ingrediente.IngredienteRepository
import validator.validate
import validator.validateNumber

class IngredienteController(
    private val ingredienteRepository: IngredienteRepository
) {

    private val logger = KotlinLogging.logger {  }

    fun findByName(name: String): List<Ingrediente> {
        logger.debug { "Controller: Se buscan ingredientes según su nombre." }
        TODO("Not yet implemented")
    }

    fun findById(id: String): Ingrediente {
        logger.debug { "Controller: Se busca un ingrediente según su id." }
        id.validateNumber(IngredienteBadRequestException("id = $id"), 1)
        return ingredienteRepository.findById(id.toInt())?: throw IngredienteNotFoundException(id)
    }

    fun getAll(): List<Ingrediente> {
        logger.debug { "Controller: Se consiguen ingredientes." }
        return ingredienteRepository.getAll()
    }

    fun add(entity: Ingrediente): Ingrediente {
        logger.debug { "Controller: Se añade un ingrediente." }
        entity.validate()
        return ingredienteRepository.add(entity) ?: throw IngredienteAlreadyExistsException("${entity.id}")
    }

    fun delete(id: Int): Ingrediente {
        logger.debug { "Controller: Se elimina un ingrediente." }
        TODO("Not yet implemented")
    }

    fun update(entity: Ingrediente): Ingrediente {
        logger.debug { "Controller: Se actualiza un ingrediente." }
        TODO("Not yet implemented")
    }
}