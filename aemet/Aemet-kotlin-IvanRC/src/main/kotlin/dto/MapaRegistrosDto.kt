package dto

import com.squareup.moshi.Json
import org.simpleframework.xml.ElementMap
import org.simpleframework.xml.Root

@Json(name = "mapa_registros")
@Root(name = "mapa_registros")
data class MapaRegistrosDto (
    @Json(name = "mapa_registros")
    @field:ElementMap(name = "mapa_registros", inline = true)
    @param:ElementMap(name = "mapa_registros", inline = true)
    val mapa_registros: Map<String, RegistroDto>
)