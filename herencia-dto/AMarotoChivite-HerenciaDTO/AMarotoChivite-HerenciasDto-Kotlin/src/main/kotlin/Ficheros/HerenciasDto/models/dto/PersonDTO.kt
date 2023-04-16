package Ficheros.HerenciasDto.models.dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

sealed class PersonDTO {
    abstract val name: String
    abstract val edad: String
    abstract val type: String

    @Root(name = "alumno") // Para XML
    data class Alumno(
        @field:Element(name = "nombreAlumno") // Para XML escritura
        @param:Element(name = "nombreAlumno")   // Para XML lectura
        override val name: String,
        @field:Element(name = "edad")
        @param:Element(name = "edad")
        override val edad: String,
        @field:Attribute(name = "type")
        @param:Attribute(name = "type")
        override val type: String = "Alumno"
    ) : PersonDTO() {

        override fun toString(): String {
            return "Alumno(name='$name', edad=$edad)"
        }
    }
    
    @Root(name = "profesor") // Para XML
    data class Profesor(
        @field:Element(name = "nombreProfesor") // Para XML escritura
        @param:Element(name = "nombreProfesor") // Para XML lectura
        override val name: String,
        @field:Element(name = "edad")
        @param:Element(name = "edad")
        override val edad: String,
        @field:Element(name = "modulo")
        @param:Element(name = "modulo")
        val modulo: String,
        @field:Attribute(name = "type")
        @param:Attribute(name = "type")
        override val type: String = "Profesor"
    ) : PersonDTO() {

        override fun toString(): String {
            return "Profesor(name='$name',edad=$edad, modulo='$modulo')"
        }
    }
}


