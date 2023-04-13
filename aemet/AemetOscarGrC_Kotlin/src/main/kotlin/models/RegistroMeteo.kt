package models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable
@Root(name = "registroMeteo")
data class RegistroMeteo(
   @field:Element(name = "nombreEstacion") var nombreEstacion: String,
   @field:Element(name = "provincia") var provincia: String,
   @field:Element(name = "temperaturaMax") var temperaturaMax: RegistroTemperatura,
   @field:Element(name = "temperaturaMin") var temperaturaMin: RegistroTemperatura,
   @field:Element(name = "precipitacion") var precipitacion: String,
   // pongo un valor nuevo para jugar con el opcional en xml. Se puede Borrar
   @field:Element(name = "fecha", required = false) var fecha: String? = null
): Serializable {

   override fun toString(): String {
      if(fecha== null){
      return "Estacion:$nombreEstacion, Provincia:$provincia TempMax:${temperaturaMax.temperatura} a las " +
              "${temperaturaMax.horaTemperatura}, TempMin:${temperaturaMin.temperatura}" +
              " a las ${temperaturaMin.horaTemperatura}\n"}
      else  return "Estacion:$nombreEstacion, Provincia:$provincia TempMax:${temperaturaMax.temperatura} a las " +
              "${temperaturaMax.horaTemperatura}, TempMin:${temperaturaMin.temperatura} " +
              "a las ${temperaturaMin.horaTemperatura} del ${fecha}\n"
   }
}