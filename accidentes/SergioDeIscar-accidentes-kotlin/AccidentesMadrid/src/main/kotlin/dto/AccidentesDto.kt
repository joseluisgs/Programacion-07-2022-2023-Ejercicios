package dto

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "accidentes")
data class AccidentesDto(
    @field:ElementList(name = "accidente", inline = true)
    @param:ElementList(name = "accidente", inline = true)
    val accidnetes: List<AccidenteDto>
)