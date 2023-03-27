package dto

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "informes")
data class InformesDto(
    @field:ElementList(name = "informe", inline = true)
    @param:ElementList(name = "informe", inline = true)
    val informes: List<InformeDto>
)
