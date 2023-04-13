package dto

import com.squareup.moshi.Json
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Json(name = "mapa_informes")
@Root(name = "mapa_informes")
data class InformesDto(
    @Json(name = "mapa_informes")
    @field:ElementList(name = "mapa_informes", inline = true)
    @param:ElementList(name = "mapa_informes", inline = true)
    val conjuntoInformesDto: List<MapaRegistrosDto>
)