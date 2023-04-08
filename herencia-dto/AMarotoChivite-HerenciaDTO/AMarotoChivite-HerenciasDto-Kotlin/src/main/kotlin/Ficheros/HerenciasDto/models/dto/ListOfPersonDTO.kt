package Ficheros.HerenciasDto.models.dto

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "listPersonas") // Para XML
class ListOfPersonDTO {
    @field:ElementList(name = "lista_personas", entry = "persona", inline = true) // Para XML
    var myList: List<PersonDTO>? = null
}