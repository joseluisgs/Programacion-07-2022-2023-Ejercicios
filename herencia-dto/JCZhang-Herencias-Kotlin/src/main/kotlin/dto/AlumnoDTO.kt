package dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "alumno")
data class AlumnoDTO(

    @field: Element(name = "nombre")
    @param: Element(name = "nombre")
    val nombre: String,

    @field: Attribute(name = "edad")
    @param: Attribute(name = "edad")
    val edad: Int
)

@Root(name = "alumnos")
data class AlumnoListDto(
    @field: ElementList(name = "alumno", inline = true)
    @param: ElementList(name = "alumno", inline = true)
    val alumnos: List<AlumnoDTO>

)
