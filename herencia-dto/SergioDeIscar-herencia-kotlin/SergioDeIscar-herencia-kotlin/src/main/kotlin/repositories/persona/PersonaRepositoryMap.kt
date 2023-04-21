package repositories.persona

import models.Alumno
import models.Persona
import models.Profesor
import mu.KotlinLogging
import service.storage.persona.PersonaStorageService

private val logger = KotlinLogging.logger {  }

object PersonaRepositoryMap: PersonaRepository {
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

    override fun save(element: Persona): Persona {
        logger.debug { "PersonaRepositoryMap ->\tsave" }
        if (element.id <= 0){ // Simula el comportamiento de un autoincremental
            element.id = personas.keys.maxOrNull()?.plus(1) ?: 1
        }
        personas[element.id] = element
        return element
    }

    override fun saveAll(elements: Iterable<Persona>) {
        logger.debug { "PersonaRepositoryMap ->\tsaveAll" }
        elements.forEach { save(it) }
    }

    override fun deleteById(id: Long): Boolean {
        logger.debug { "PersonaRepositoryMap ->\tdeleteById" }
        val result = personas.remove(id) != null
        return result
    }

    override fun delete(element: Persona): Boolean {
        logger.debug { "PersonaRepositoryMap ->\tdelete" }
        return deleteById(element.id)
    }

    override fun deleteAll() {
        logger.debug { "PersonaRepositoryMap ->\tdeleteAll" }
        personas.clear()
    }

    override fun existsById(id: Long): Boolean {
        logger.debug { "PersonaRepositoryMap ->\texistsById" }
        return findById(id) != null
    }
}