package dto

import com.squareup.moshi.Json
import org.simpleframework.xml.ElementList


data class ListaMedicionesDto (
    @Json(name = "mediciones")
    @field:ElementList(name = "mediciones", inline = true)
    @param:ElementList(name = "mediciones", inline = true)
    val mediciones: List<MedicionDto>
)