package dto.conjuntoDeAccidentes

import com.squareup.moshi.Json
import dto.accidente.ListaAccidentesDto
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "conjunto_de_accidentes")
data class ConjuntoDeAccidenteDto (
    @Json(name = "accidentes")
    @field:Element(name = "accidentes")
    @param:Element(name = "accidentes")
    val accidentes: ListaAccidentesDto
)