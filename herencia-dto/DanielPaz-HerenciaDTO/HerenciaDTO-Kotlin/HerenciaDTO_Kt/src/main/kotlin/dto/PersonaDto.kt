package dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root


@Root(name = "persona")
data class PersonaDto(

    @field:Attribute(name = "nombre")
    @param:Attribute(name = "nombre")
    val nombre :String,

    @field:Attribute(name = "tipo")
    @param:Attribute(name = "tipo")
    val tipo: String,

    @field:Attribute(name = "modulo")
    @param:Attribute(name = "modulo")
    val modulo: String?,


    @field:Attribute(name = "edad")
    @param:Attribute(name = "edad")
    val edad: String?
)