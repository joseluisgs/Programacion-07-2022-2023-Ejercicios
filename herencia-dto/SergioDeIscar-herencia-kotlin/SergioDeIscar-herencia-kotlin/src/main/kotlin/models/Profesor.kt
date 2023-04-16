package models

import dto.PersonaDto
import java.io.Serializable

class Profesor(
    id: Long = 0,
    nombre: String,
    val modulo: String
): Persona(id, nombre), Serializable {
    override fun toCsvRow(): String {
        return "$id,$nombre,Profesor,null,$modulo\n"
    }

    override fun copy(id: Long, nombre: String): Persona {
        this.id = id
        return Profesor(id, nombre, modulo)
    }

    override fun toString(): String {
        return "Profesor(id=$id, nombre='$nombre', modulo='$modulo')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Profesor) return false
        return id == other.id && nombre == other.nombre && modulo == other.modulo
    }
}
