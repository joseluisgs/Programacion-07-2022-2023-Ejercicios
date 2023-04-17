package dto

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "Persona")
data class PersonaDto(
    @field:Element(name = "name")
    @param:Element(name= "name")
    val name: String,

    @field:Element(name = "modulo", required = false)
    @param:Element(name = "modulo", required = false)
    val modulo: String?,

    @field:Element(name = "edad", required = false)
    @param:Element(name = "edad", required = false)
    val edad: String?
)

@Root(name = "Lista_Persona")
data class ListaPersonaDto(
    @field:ElementList(name = "Lista_Persona", inline = true)
    @param:ElementList(name = "Lista_Persona", inline = true)
    val listaPersona: List<PersonaDto>
)