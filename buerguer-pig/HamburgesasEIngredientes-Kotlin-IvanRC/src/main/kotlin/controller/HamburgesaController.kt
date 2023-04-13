package controller

import exception.hamburgesaException.HamburgesaAlreadyExistsException
import exception.hamburgesaException.HamburgesaNotFoundException
import model.Hamburgesa
import model.Ingrediente
import mu.KotlinLogging
import repository.Hamburger.HamburgesaRepository
import repository.Ingrediente.IngredienteRepository
import validator.validarListaDeIngredientes
import validator.validate
import java.util.*

class HamburgesaController(
    private val hamburgesaRepository: HamburgesaRepository,
    private val ingredienteRepository: IngredienteRepository
) {

    private val logger = KotlinLogging.logger {  }

    fun findByName(name: String): List<Ingrediente> {
        logger.debug { "Controller: Se buscan ingredientes según su nombre." }
        TODO("Not yet implemented")
    }

    fun findById(id: String): Hamburgesa {
        logger.debug { "Controller: Se busca una hamburgesa según su id." }
        return hamburgesaRepository.findById(UUID.fromString(id))?: throw HamburgesaNotFoundException(id)
    }

    fun getAll(): List<Hamburgesa> {
        logger.debug { "Controller: Se consiguen hamburgesas." }
        return hamburgesaRepository.getAll()
    }

    fun add(entity: Hamburgesa): Hamburgesa{
        logger.debug { "Controller: Se añade una hamburgesa." }
        entity.validate()
        return hamburgesaRepository.add(entity) ?: throw HamburgesaAlreadyExistsException("${entity.id}")
    }

    fun addComoSiFueraVentaLVenta(nombre: String, ingredientes: List<Int>): Hamburgesa {
        logger.debug { "Controller: Se añade una hamburgesa." }
        val hamburgesa = Hamburgesa(UUID.randomUUID(), nombre, mutableListOf())
        validarListaDeIngredientes(ingredientes, ingredienteRepository)
        crearIngredientes(ingredientes, hamburgesa)
        hamburgesa.validate()
        return hamburgesaRepository.add(hamburgesa) ?: throw HamburgesaAlreadyExistsException("${hamburgesa.id}")
    }

    fun crearIngredientes(ingredientes: List<Int>, hamburgesa: Hamburgesa) {
        ingredientes.forEach {
            val ingrediente = ingredienteRepository.findById(it)
            hamburgesa.ingredientes.add(ingrediente!!)
        }
    }

    fun delete(id: Int): Hamburgesa {
        logger.debug { "Controller: Se elimina una hamburgesa." }
        TODO("Not yet implemented")
    }

    fun update(entity: Hamburgesa): Hamburgesa {
        logger.debug { "Controller: Se actualiza una hamburgesa." }
        TODO("Not yet implemented")
    }
}