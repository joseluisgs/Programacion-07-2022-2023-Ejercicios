package models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable

data class Alumno(
    var edad:Int,
    override var nombre:String): Persona(), Serializable {
    override fun toString(): String {
        return "Hola soy ${nombre}, soy Alumno de ${edad} años \n"
    }
}