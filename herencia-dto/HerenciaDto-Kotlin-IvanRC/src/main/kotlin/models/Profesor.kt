package models

class Profesor(
    nombre: String,
    val modulo: String
) : Persona(nombre){
    override fun toString(): String {
        return "Profesor(nombre='$nombre', modulo='$modulo')"
    }
}