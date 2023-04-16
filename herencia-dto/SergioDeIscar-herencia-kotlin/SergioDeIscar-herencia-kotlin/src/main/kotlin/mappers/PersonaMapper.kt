package mappers

import dto.PersonaDto
import models.Persona

fun Persona.toDto(): PersonaDto {
    return when(this){
        is models.Alumno -> PersonaDto(id.toString(), nombre, "Alumno", edad.toString(), null)
        is models.Profesor -> PersonaDto(id.toString(), nombre, "Profesor", null, modulo)
        else -> throw Exception("Tipo de persona desconocido")
    }
}

fun PersonaDto.toClass(): Persona {
    return when(tipo){
        "Alumno" -> models.Alumno(id.toLong(), nombre, edad!!.toInt())
        "Profesor" -> models.Profesor(id.toLong(), nombre, modulo!!)
        else -> throw Exception("Tipo de persona desconocido")
    }
}