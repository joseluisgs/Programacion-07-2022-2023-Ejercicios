package models

import java.io.Serializable

class Profesor(nombre: String, val modulo: String) : Persona(nombre),Serializable{

    override fun toString(): String {
        return "$nombre $modulo\n"
    }
}