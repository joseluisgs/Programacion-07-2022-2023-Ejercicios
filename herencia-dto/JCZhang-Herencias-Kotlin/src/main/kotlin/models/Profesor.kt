package models

import enums.modulos

class Profesor(nombre: String, var modulo: modulos): Persona(nombre) {
    override fun toString(): String {
        return "Profesor(nomnre = $nombre, modulo=$modulo)"
    }
}