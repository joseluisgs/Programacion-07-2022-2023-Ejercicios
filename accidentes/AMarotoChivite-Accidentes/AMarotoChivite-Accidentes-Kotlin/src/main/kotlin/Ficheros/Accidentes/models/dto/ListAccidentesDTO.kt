package Ficheros.Accidentes.models.dto

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "listAccidentes")
class ListAccidentesDTO {
    @field:ElementList(name = "lista_accidente", entry = "accidente", inline = true)
    var myList: List<AccidenteDTO>? = null
}

