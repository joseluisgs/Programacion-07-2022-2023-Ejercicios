package controller

import models.Alumno
import models.Persona
import repositories.persona.PersonaRepository

class PersonasController(
    private val personaRepository: PersonaRepository
) {
    fun findAll(): List<Persona> {
        return personaRepository.findAll()
    }

    fun save(entity: Persona): Persona {
        return personaRepository.save(entity)
    }

    fun alumnoJoven(): Alumno? {
        return personaRepository.alumnoJoven()
    }

    fun mediaAlumnoEdad(): Int {
        return personaRepository.mediaAlumnoEdad()
    }

    fun groupByTipo(): List<Persona> {
        return personaRepository.groupByTipo()
    }

    fun mediaNombreLogitud(): Double {
        return personaRepository.mediaNombreLogitud()
    }
}