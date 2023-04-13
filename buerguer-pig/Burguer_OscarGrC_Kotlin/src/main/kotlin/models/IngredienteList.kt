package models

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.io.Serializable

@Root(name ="IngredienteList" )
data class IngredienteList(
    @field:ElementList(name = "listaHamburguesas", inline = true)
    @param:ElementList(name = "listaHamburguesas", inline = true)  var listaIngredientes:List<Ingrediente>)
    : Serializable {
}