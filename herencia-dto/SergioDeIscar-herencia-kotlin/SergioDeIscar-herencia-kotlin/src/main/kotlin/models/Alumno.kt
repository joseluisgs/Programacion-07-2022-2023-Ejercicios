package models

import dto.PersonaDto
import java.io.Serializable

class Alumno(
    id: Int? = null,
    nombre: String,
    val edad: Int
) : Persona(id ?: count++, nombre), Serializable {
    override fun toDto(): PersonaDto {
        return PersonaDto(id.toString(), nombre, "Alumno", edad.toString(), null)
    }

    override fun toCsvRow(): String {
        return "$id,$nombre,Alumno,$edad,null\n"
    }

    override fun toString(): String {
        return "Alumno(id=$id, nombre='$nombre', edad=$edad)"
    }
}