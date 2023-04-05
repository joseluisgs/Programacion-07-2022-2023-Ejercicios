package models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import org.simpleframework.xml.Serializer
import java.io.Serializable

@Root(name = "RegistroTemperatura")
data class RegistroTemperatura(@field:Element(name = "temperatura") var temperatura:String,
                               @field:Element(name = "horaTemperatura") var horaTemperatura:String): Serializable {

}
