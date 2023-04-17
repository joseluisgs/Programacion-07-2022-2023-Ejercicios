package models

data class Alumno(override val name: String, val edad: Int): Persona(name) {
}