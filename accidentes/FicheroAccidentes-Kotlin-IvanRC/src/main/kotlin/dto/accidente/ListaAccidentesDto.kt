package dto.accidente

import com.squareup.moshi.Json
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "accidentes")
data class ListaAccidentesDto(
    @Json(name = "accidentes")
    @field:ElementList(name = "accidentes", inline = true)
    @param:ElementList(name = "accidentes", inline = true)
    val accidentesDto: List<AccidenteDto>
)