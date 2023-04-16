package repositories.persona

import models.Persona
import repositories.CrudRepository

interface PersonaExtension: CrudRepository<Persona, Long>{
    fun getPorcentajePorTipo(): Map<String, Double>
}