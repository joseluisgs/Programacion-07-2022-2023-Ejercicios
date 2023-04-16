package controllers.persona

import controllers.CrudController
import errors.PersonaError
import models.Persona

interface IPersonaController: CrudController<Persona, Long, PersonaError>{
    fun getPorcentajePorTipo(): Map<String, Double>
}