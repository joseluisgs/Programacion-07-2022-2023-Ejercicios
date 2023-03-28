package repositories.persona

import models.Persona
import mu.KotlinLogging
import service.storage.persona.PersonaStorageService

private val logger = KotlinLogging.logger {  }

class PersonaRepositoryMap(
    val storageService: PersonaStorageService
): PersonaRepository {
    private val personas = mutableMapOf<Int, Persona>()

    override fun getAll(): List<Persona> {
        logger.debug { "PersonaRepositoryMap ->\tgetAll" }
        return personas.values.toList()
    }

    override fun getById(id: Int): Persona? {
        logger.debug { "PersonaRepositoryMap ->\tgetById" }
        return personas[id]
    }

    override fun save(element: Persona, storage: Boolean): Persona {
        logger.debug { "PersonaRepositoryMap ->\tsave" }
        personas[element.id] = element
        if (storage) downgrade()
        return element
    }

    override fun saveAll(elements: List<Persona>, storage: Boolean) {
        logger.debug { "PersonaRepositoryMap ->\tsaveAll" }
        elements.forEach { save(it, storage) }
    }

    override fun deleteById(id: Int): Persona? {
        logger.debug { "PersonaRepositoryMap ->\tdeleteById" }
        return personas.remove(id)
    }

    override fun update(element: Persona): Persona? {
        logger.debug { "PersonaRepositoryMap ->\tupdate" }
        return updateById(element.id, element)
    }

    override fun updateById(id: Int, element: Persona): Persona? {
        logger.debug { "PersonaRepositoryMap ->\tupdateById" }
        personas[id] ?: return null
        personas[id] = element
        return element
    }

    override fun upgrade(): List<Persona> {
        logger.debug { "PersonaRepositoryMap ->\tupgrade" }
        personas.clear()
        val load = storageService.loadAll()
        saveAll(load, false)
        return load
    }

    override fun downgrade(): List<Persona> {
        logger.debug { "PersonaRepositoryMap ->\tdowngrade" }
        return storageService.saveAll(personas.values.toList())
    }
}