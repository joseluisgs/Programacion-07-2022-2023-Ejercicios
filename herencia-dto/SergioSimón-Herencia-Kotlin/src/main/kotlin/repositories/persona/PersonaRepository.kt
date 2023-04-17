package repositories.persona

import models.Alumno
import models.Persona
import repositories.BaseRepository

interface PersonaRepository: BaseRepository<Persona> {
    fun alumnoJoven(): Alumno?
    fun mediaAlumnoEdad(): Int
    fun groupByTipo(): List<Persona>
    fun mediaNombreLogitud(): Double
}