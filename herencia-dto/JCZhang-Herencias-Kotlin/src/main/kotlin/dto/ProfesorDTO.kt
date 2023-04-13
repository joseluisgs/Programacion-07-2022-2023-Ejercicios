package dto

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "profesor")
data class ProfesorDTO(

    @field: Element(name = "nombre")
    @param: Element(name = "nombre")
    val nombre:String,

    @field: Element(name = "modulo")
    @param: Element(name = "modulo")
    val modulo: String
)

@Root(name = "profesores")
data class ProfesorListDto(
    @field: ElementList(name = "profesores", inline = true)
    @param: ElementList(name = "profesores", inline = true)
    val profesores: List<ProfesorDTO>
)