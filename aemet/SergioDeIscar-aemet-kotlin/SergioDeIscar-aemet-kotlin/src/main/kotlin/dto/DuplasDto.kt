package dto

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "duplas")
data class DuplasDto(
    @field:ElementList(name = "dupla", inline = true)
    @param:ElementList(name = "dupla", inline = true)
    val duplas: List<DuplaDto>
)
