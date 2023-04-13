package models

class Alumno(
    nombre: String,
    val edad: Int
): Persona(nombre) {
    override fun toString(): String {
        return "Alumno(nombre='$nombre', edad='$edad')"
    }
}