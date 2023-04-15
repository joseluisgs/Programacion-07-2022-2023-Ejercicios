package repositories.persona

import models.Persona
import repositories.CrudRepository

interface PersonaExtension: CrudRepository<Persona, Int>{
    fun getPorcentajePorTipo(): Map<String, Double>
}