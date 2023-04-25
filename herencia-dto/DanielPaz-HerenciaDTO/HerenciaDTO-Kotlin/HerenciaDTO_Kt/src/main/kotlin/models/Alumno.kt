package models

import java.io.Serializable

class Alumno(nombre:String,val edad:Int):Persona(nombre),Serializable{

    override fun toString(): String {
        return "$nombre $edad\n"
    }
}