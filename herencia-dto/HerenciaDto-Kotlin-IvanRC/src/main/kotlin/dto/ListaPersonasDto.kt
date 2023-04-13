package dto

import com.squareup.moshi.Json
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Json(name = "personas")
@Root(name = "personas")
data class ListaPersonasDto (
    @Json(name = "personas")
    @field:ElementList(name = "personas", inline = true)
    @param:ElementList(name = "personas", inline = true)
    val personasDto: List<PersonaDto>
)