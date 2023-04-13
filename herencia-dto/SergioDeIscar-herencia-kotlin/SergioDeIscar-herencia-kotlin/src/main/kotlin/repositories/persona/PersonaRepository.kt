package repositories.persona

import models.Persona
import repositories.IExternalStore

interface PersonaRepository: PersonaExtension, IExternalStore<Persona>