package dto.hamburgesa

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root
data class ListaHamburgesasDto(
    @field:ElementList(name = "hamburgesas", inline = true)
    @param:ElementList(name = "hamburgesas", inline = true)
    val hamburgesasDto: List<HamburgesaDto>
)