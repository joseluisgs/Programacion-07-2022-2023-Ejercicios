package dto.lesividad

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "lesividad")
data class LesividadDto (
    @field:Element(name = "id_lesividad")
    @param:Element(name = "id_lesividad")
    val id: String, // 13
    @field:Element(name = "lesividad")
    @param:Element(name = "lesividad")
    val lesividad: String // 14
)
