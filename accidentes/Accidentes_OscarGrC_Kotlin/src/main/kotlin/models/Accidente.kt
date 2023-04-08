package models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable
@Root(name = "Accidente")
data class Accidente(
  @field:Element(name = "NUM_EXPEDIENTE")var num_expediente: String = "",
  @field:Element(name = "FECHA") var fecha: String = "",
  @field:Element(name = "HORA")  var hora: String = "",
  @field:Element(name = "LOCALIZACION") var localizacion: String = "",
  @field:Element(name = "NUMERO",required = false)  var numero: String = "",
  @field:Element(name = "COD_DISTRITO",required = false) var cod_distrito: String = "",
  @field:Element(name = "DISTRITO") var distrito: String = "",
  @field:Element(name = "TIPO_ACCIDENTE") var tipo_accidente: String = "",
  @field:Element(name = "ESTADO_METEOROLOGICO") var estado_meteorologico: String = "",
  @field:Element(name = "TIPO_VEHICULO")var tipo_vehiculo: String = "",
  @field:Element(name = "TIPO_PERSONA") var tipo_persona: String = "",
  @field:Element(name = "RANGO_EDAD") var rango_edad: String = "",
  @field:Element(name = "SEXO")  var sexo: String = "",
  @field:Element(name = "COD_LESIONIDAD") var cod_lesividad: String = "",
  @field:Element(name = "LESIVIDAD") var lesividad: String = "",
  @field:Element(name = "COORDENADA_X_UTM") var coordenada_x_utm: String = "",
  @field:Element(name = "COORDENADA_Y_UTM") var coordenada_y_utm: String = "",
  @field:Element(name = "POSITIVA_ALCOHOL") var positiva_alcohol: String = "",
  @field:Element(name = "POSITIVA_DROGA")  var positiva_droga: String = ""
) {
  constructor(it: Accidente) : this(
    num_expediente = it.num_expediente,
    fecha = it.fecha,
    hora = it.hora,
    localizacion = it.localizacion,
    numero = it.numero,
    cod_distrito = it.cod_distrito,
    distrito = it.distrito,
    tipo_accidente = it.tipo_accidente,
    estado_meteorologico = it.estado_meteorologico,
    tipo_vehiculo= it.tipo_vehiculo,
    tipo_persona= it.tipo_persona,
    rango_edad= it.rango_edad,
    sexo= it.sexo,
    cod_lesividad= it.cod_lesividad,
    lesividad=it.lesividad,
    coordenada_x_utm=it.coordenada_x_utm,
    coordenada_y_utm= it.coordenada_y_utm,
    positiva_alcohol= it.positiva_alcohol,
    positiva_droga= it.positiva_droga)
  override fun toString(): String {
        return  "num_expediente:$num_expediente, tipoAccidente:$tipo_accidente " +
                " fecha:$fecha, hora:$hora, localizacion:$localizacion, " +
                "numero:$numero,cod_distrito:$cod_distrito,distrito:$distrito, "+
                "estado_meteorológico:$estado_meteorologico, tipo_vehiculo:$tipo_vehiculo, tipo_persona:$tipo_persona" +
                "rango_edad:$rango_edad, sexo:$sexo, cod_lesividad:$cod_lesividad, lesividad:$lesividad, " +
                "coordenada_x: $coordenada_x_utm, coordenada_y:$coordenada_y_utm,positiva_alcohol:$positiva_alcohol, " +
                "positiva_droga:$positiva_droga \n"
    }
}