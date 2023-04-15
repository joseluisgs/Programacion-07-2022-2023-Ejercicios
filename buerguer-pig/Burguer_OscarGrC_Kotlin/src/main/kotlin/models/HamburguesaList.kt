package models

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.io.Serializable

@Root(name ="HamburguesaList" )
data class HamburguesaList(
    @field:ElementList(name = "listaHamburguesas", inline = true)
    @param:ElementList(name = "listaHamburguesas", inline = true)  var listaHamburguesas:List<Hamburgesa>)
    : Serializable {
}