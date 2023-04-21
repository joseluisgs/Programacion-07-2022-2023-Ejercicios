package dto

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import org.simpleframework.xml.Serializer

@Root(name = "personas")
data class PersonasDto(
    @field:ElementList(name = "persona", inline = true)
    @param:ElementList(name = "persona", inline = true)
    var personas: List<PersonaDto>
)