package models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable

data class Profesor (
    var modulo:String,
    override var nombre:String): Persona(),Serializable {
    override fun toString(): String {
        return "Hola soy ${nombre}, soy Profesor de $modulo \n"
    }
}