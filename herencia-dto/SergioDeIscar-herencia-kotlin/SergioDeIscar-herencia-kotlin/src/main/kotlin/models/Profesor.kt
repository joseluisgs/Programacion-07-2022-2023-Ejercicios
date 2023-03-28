package models

import dto.PersonaDto
import java.io.Serializable

class Profesor(
    id: Int? = null,
    nombre: String,
    val modulo: String
): Persona(id ?: count++, nombre), Serializable {
    override fun toDto(): PersonaDto {
        return PersonaDto(id.toString(), nombre, "Profesor", null, modulo)
    }

    override fun toCsvRow(): String {
        return "$id,$nombre,Profesor,null,$modulo\n"
    }
}
