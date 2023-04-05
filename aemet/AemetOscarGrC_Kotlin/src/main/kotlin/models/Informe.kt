package models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

//ratliff style
@Root(name = "Informe")
data class Informe (
        @field:Element(name = "fecha")
        val fecha:String,
        @field:Element(name = "temperaturaMedia")
        val temperaturaMedia:String,
        @field:Element(name = "temperaturaMaxima")
        val temperaturaMaxima:String,
        @field:Element(name = "horaTemperaturaMaxima")
        val horaTemperaturaMaxima:String,
        @field:Element(name = "estacionTemperaturaMaxima")
        val estacionTemperaturaMaxima:String,
        @field:Element(name = "temperaturaMinima")
        val temperaturaMinima:String,
        @field:Element(name = "horaTemperaturaMinima")
        val horaTemperaturaMinima:String,
        @field:Element(name = "estacionTemperaturaMinima")
        val estacionTemperaturaMinima:String,
        @field:Element(name = "precipitacion")
        val precipitacion:Boolean,
        @field:Element(name = "ListaSitiosPrecipitacion")
        val cantidadYLugar:Array<String>
){


        @field:Element(name = "Datos")
        var informe:String ="      INFORME DIARIO COMUNIDAD DE MADRID DIA:${fecha}\n" +
                "      ---------------------------------------------------- \n" +
                "      - Temperatura Media Comunidad: ${temperaturaMedia}ºC\n" +
                "      - Temperatura Maxima (Lugar y Momento): ${temperaturaMaxima}ºC" +
                "en estacion de ${estacionTemperaturaMaxima} a las ${horaTemperaturaMaxima}\n" +
                "      - Temperatura Minima (Lugar y Momento): ${temperaturaMinima}ºC" +
                "en estacion de ${estacionTemperaturaMinima} a las ${horaTemperaturaMinima}\n" +
                "      - Precipitaciones: ${precipitacion} \n" +
                InformeB()
        private fun InformeB():String{
                var informe:String=""
                cantidadYLugar.forEach {
                        informe+= it
                }
                return informe
        }
}