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
        return personas.values.toList()
    }

    override fun findById(id: Long): Persona? {
        logger.debug { "PersonaRepositoryMap ->\tgetById" }
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
        return personas.remove(id) != null
    }

    override fun delete(element: Persona): Boolean {
        return deleteById(element.id)
    }

    override fun existsById(id: Long): Boolean {
        logger.debug { "PersonaRepositoryMap ->\texistsById" }
        return personas.containsKey(id)
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