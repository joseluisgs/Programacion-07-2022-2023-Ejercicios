package dto.distrito

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "distrito")
data class DistritoDto (
    @field:Element(name = "id_distrito")
    @param:Element(name = "id_distrito")
    val id: String, // 5
    @field:Element(name = "nombre")
    @param:Element(name = "nombre")
    val nombre: String // 6
)
