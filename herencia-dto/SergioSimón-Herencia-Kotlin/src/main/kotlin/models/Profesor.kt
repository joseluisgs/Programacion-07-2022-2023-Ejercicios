package models

data class Profesor(override val name: String, val modulo: String): Persona(name)

enum class Modulo(){
    PROGRAMACIÓN, ENTORNOS
}