package models

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.io.Serializable
@Root(name = "registroDiarioRM")
class RegistroDiarioRM(@field:Element(name = "dia")  val dia:String,@field:ElementList(inline = true, required = false)
                       var registros:MutableList<RegistroMeteo>): Serializable {

}

