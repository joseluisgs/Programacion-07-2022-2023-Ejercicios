package dto

import com.squareup.moshi.Json
import org.simpleframework.xml.ElementMap
import org.simpleframework.xml.Root

@Json(name = "mapa_mediciones")
@Root(name = "mapa_mediciones")
data class MapaMedicionesDto (
    @Json(name = "mapa_mediciones")
    @field:ElementMap(name = "mapa_mediciones", inline = true)
    @param:ElementMap(name = "mapa_mediciones", inline = true)
    val medicionesDto: Map<String, ListaMedicionesDto>
)