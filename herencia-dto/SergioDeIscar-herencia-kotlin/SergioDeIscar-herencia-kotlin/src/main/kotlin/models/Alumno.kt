package models

import dto.PersonaDto
import java.io.Serializable

class Alumno(
    id: Long = 0,
    nombre: String,
    val edad: Int
) : Persona(id, nombre), Serializable {

    override fun toCsvRow(): String {
        return "$id,$nombre,Alumno,$edad,null\n"
    }

    override fun copy(id: Long, nombre: String): Persona {
        this.id = id
        return Alumno(id, nombre, edad)
    }

    override fun toString(): String {
        return "Alumno(id=$id, nombre='$nombre', edad=$edad)"
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Alumno) return false
        return id == other.id && nombre == other.nombre && edad == other.edad
    }
}