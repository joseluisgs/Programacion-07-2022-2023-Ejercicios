package models

import java.io.Serializable

open class Persona(open val name: String): Serializable {
    fun toStringCsv(): String {
        if (this is Alumno) {
            return "${this.name}, ${this.edad}, null, Alumno"
        }
        else if (this is Profesor) {
            return "${this.name}, null, ${this.modulo}, Profesor"
        }
        return "${this.name}, null, null, Persona"
    }
}
