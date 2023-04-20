package repositories.persona

import models.Persona
import repositories.CrudRepository

interface PersonaRepository: CrudRepository<Persona, Long>{
    fun getPorcentajePorTipo(): Map<String, Double>
}