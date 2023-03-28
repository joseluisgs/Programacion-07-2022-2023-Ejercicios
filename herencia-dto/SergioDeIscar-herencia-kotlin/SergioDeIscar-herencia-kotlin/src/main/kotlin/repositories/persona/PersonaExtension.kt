package repositories.persona

import models.Persona
import repositories.CrudRepository

interface PersonaExtension: CrudRepository<Persona, Int>