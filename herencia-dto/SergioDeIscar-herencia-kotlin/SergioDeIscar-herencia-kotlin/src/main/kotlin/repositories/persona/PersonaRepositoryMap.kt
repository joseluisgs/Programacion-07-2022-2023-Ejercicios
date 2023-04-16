package repositories.persona

import models.Alumno
import models.Persona
import models.Profesor
import mu.KotlinLogging
import service.storage.persona.PersonaStorageService

private val logger = KotlinLogging.logger {  }

class PersonaRepositoryMap(
    private val storageService: PersonaStorageService
): PersonaExtension {
    private val personas = mutableMapOf<Long, Persona>()
    override fun getPorcentajePorTipo(): Map<String, Double> {
        logger.debug { "PersonaRepositoryMap ->\tgetPorcentajePorTipo" }
        upgrade()
        return personas.values
            .groupBy {
                when(it){
                    is Alumno -> "Alumno"
                    is Profesor -> "Profesor"
                    else -> throw RuntimeException("Tipo de persona no reconocido")
                }
            }
            .mapValues { it.value.size.toDouble() / personas.size }
    }

    override fun findAll(): Iterable<Persona> {
        logger.debug { "PersonaRepositoryMap ->\tgetAll" }
        upgrade()
        return personas.values.toList()
    }

    override fun findById(id: Long): Persona? {
        logger.debug { "PersonaRepositoryMap ->\tgetById" }
        upgrade()
        return personas[id]
    }

    override fun save(element: Persona, storage: Boolean): Persona {
        logger.debug { "PersonaRepositoryMap ->\tsave" }
        personas[element.id] = element
        if (storage) downgrade()
        return element
    }

    override fun saveAll(elements: Iterable<Persona>, storage: Boolean) {
        logger.debug { "PersonaRepositoryMap ->\tsaveAll" }
        elements.forEach { save(it, false) }
        if (storage) downgrade()
    }

    override fun deleteById(id: Long): Boolean {
        logger.debug { "PersonaRepositoryMap ->\tdeleteById" }
        val result = personas.remove(id) != null
        downgrade()
        return result
    }

    override fun delete(element: Persona): Boolean {
        logger.debug { "PersonaRepositoryMap ->\tdelete" }
        return deleteById(element.id)
    }

    override fun existsById(id: Long): Boolean {
        logger.debug { "PersonaRepositoryMap ->\texistsById" }
        return findById(id) != null
    }

    private fun upgrade(): List<Persona> {
        logger.debug { "PersonaRepositoryMap ->\tupgrade" }
        personas.clear()
        val load = storageService.loadAll()
        saveAll(load, false)
        return load
    }

    private fun downgrade(): List<Persona> {
        logger.debug { "PersonaRepositoryMap ->\tdowngrade" }
        return storageService.saveAll(personas.values.toList())
    }
}