package models

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.io.Serializable

@Root(name = "PersonasDTOList")
class PersonasDTOList(
    @field:ElementList(name = "listaPersonasDto", inline = true)
    @param:ElementList(name = "listaPersonasDto", inline = true)  var listaPersonasDto:List<PersonaDTO>):Serializable{
constructor(): this(mutableListOf())
}