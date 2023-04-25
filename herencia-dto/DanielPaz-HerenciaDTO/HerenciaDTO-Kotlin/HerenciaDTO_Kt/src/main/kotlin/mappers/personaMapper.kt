package mappers

import dto.PersonaDto
import dto.PersonasDto
import exceptions.PersonNotValidException
import models.Alumno
import models.Persona
import models.Personas
import models.Profesor

fun Persona.toDto(): PersonaDto{

    return when (this) {
        is Alumno -> { PersonaDto(this.nombre,"Alumno","",this.edad.toString()) }

        is Profesor -> { PersonaDto(this.nombre,"Profesor",this.modulo,"") }

        else -> { throw PersonNotValidException("Persona con tipo desconocido.") }
    }
}

fun PersonaDto.toPersona(): Persona{

    return when (this.tipo) {
        "Alumno" ->{ Alumno(this.nombre, this.edad?.toInt()!!) }
        "Profesor" ->{ Profesor(this.nombre, this.modulo!!)}
        else -> { throw PersonNotValidException("Persona con tipo desconocido.")}
    }
}

fun List<Persona>.toDto() = PersonasDto(
    personas = map { it.toDto() }
)


fun PersonasDto.toPersonas() = personas.map { it.toPersona() }
