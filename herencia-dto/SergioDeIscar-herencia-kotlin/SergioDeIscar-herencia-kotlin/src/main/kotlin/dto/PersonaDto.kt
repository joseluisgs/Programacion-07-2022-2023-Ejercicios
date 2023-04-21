package dto

import models.Persona
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable

@Root(name = "persona")
data class PersonaDto(
    @field:Attribute(name = "id")
    @param:Attribute(name = "id")
    val id: String,

    @field:Element(name = "nombre")
    @param:Element(name = "nombre")
    val nombre: String,

    @field:Element(name = "tipo")
    @param:Element(name = "tipo")
    val tipo: String,

    @field:Element(name = "edad", required = false)
    @param:Element(name = "edad", required = false)
    val edad: String?,

    @field:Element(name = "modulo", required = false)
    @param:Element(name = "modulo", required = false)
    val modulo: String?
)
