package controllers

import models.Persona
import mu.KotlinLogging
import repositories.persona.PersonaExtension
import repositories.persona.PersonaRepository

private val logger = KotlinLogging.logger {}

class PersonaController(
    private val repo: PersonaRepository
): PersonaExtension {
    override fun getPorcentajePorTipo(): Map<String, Double> {
        logger.debug { "PersonaController ->\tgetPorcentajePorTipo" }
        return repo.getPorcentajePorTipo()
    }

    override fun getAll(): List<Persona> {
        logger.debug { "PersonaController ->\tgetAll" }
        return repo.getAll()
    }

    override fun getById(id: Int): Persona? {
        logger.debug { "PersonaController ->\tgetById" }
        checkId(id)
        return repo.getById(id)
    }

    override fun save(element: Persona, storage: Boolean): Persona {
        logger.debug { "PersonaController ->\tsave" }
        checkId(element.id)
        return repo.save(element, storage)
    }

    override fun saveAll(elements: List<Persona>, storage: Boolean) {
        logger.debug { "PersonaController ->\tsaveAll" }
        elements.forEach { checkId(it.id) }
        repo.saveAll(elements, storage)
    }

    override fun deleteById(id: Int): Persona? {
        logger.debug { "PersonaController ->\tdeleteById" }
        checkId(id)
        return repo.deleteById(id)
    }

    override fun update(element: Persona): Persona? {
        logger.debug { "PersonaController ->\tupdate" }
        checkId(element.id)
        return repo.update(element)
    }

    override fun updateById(id: Int, element: Persona): Persona? {
        logger.debug { "PersonaController ->\tupdateById" }
        checkId(id)
        return repo.updateById(id, element)
    }

    private fun checkId(id: Int) {
        require(id >= 0) { "Id tiene que ser mayor o igual que 0" }
    }
}