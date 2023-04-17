package mappers

import dto.ListaPersonaDto
import dto.PersonaDto
import models.Alumno
import models.Persona
import models.Profesor

fun Persona.personaDto(): PersonaDto? {
    if (this is Profesor){
        return PersonaDto(name = this.name, modulo = this.modulo, edad = null)
    } else if (this is Alumno){
        return PersonaDto(name = this.name, modulo = null, edad = this.edad.toString())
    }
    return null
}

fun PersonaDto.toPersona(): Persona? {
    if (this.edad == null){
        return Profesor(this.name, this.modulo?: "")
    } else if (this.modulo == null){
        return Alumno(this.name, this.edad.toInt())
    }
    return null
}

fun List<Persona>.personaDto() = ListaPersonaDto(
    listaPersona = map { it.personaDto()!! }
)

fun ListaPersonaDto.toPersonaList() = listaPersona.map { it.toPersona() }