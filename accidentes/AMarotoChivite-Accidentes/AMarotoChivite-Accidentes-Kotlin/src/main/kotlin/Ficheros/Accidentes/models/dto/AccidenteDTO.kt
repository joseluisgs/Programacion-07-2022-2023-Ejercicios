package Ficheros.Accidentes.models.dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element

class AccidenteDTO(
    @field:Attribute(name = "num_expediente")
    @param:Attribute(name = "num_expediente")
    val numExpediente: String,
    @field:Attribute(name = "fecha")
    @param:Attribute(name = "fecha")
    val fecha: String,
    @field:Element(name = "hora")
    @param:Element(name = "hora")
    val hora: String,
    @field:Attribute(name = "localizacion")
    @param:Attribute(name = "localizacion")
    val localizacion: String,
    @field:Attribute(name = "numero")
    @param:Attribute(name = "numero")
    val numero: String,
    @field:Element(name = "cod_distrito")
    @param:Element(name = "cod_distrito")
    val codDistrito: String,
    @field:Element(name = "distrito")
    @param:Element(name = "distrito")
    val distrito: String,
    @field:Element(name = "tipo_accidente")
    @param:Element(name = "tipo_accidente")
    val tipoAccidente: String,
    @field:Element(name = "estado_meteorologico")
    @param:Element(name = "estado_meteorologico")
    val estadoMeteorologico: String,
    @field:Element(name = "tipo_vehiculo")
    @param:Element(name = "tipo_vehiculo")
    val tipoVehiculo: String,
    @field:Element(name = "tipo_persona")
    @param:Element(name = "tipo_persona")
    val tipoPersona: String,
    @field:Element(name = "rango_edad")
    @param:Element(name = "rango_edad")
    val rangoEdad: String,
    @field:Element(name = "sexo")
    @param:Element(name = "sexo")
    val sexo: String,
    @field:Element(name = "cod_lesividad")
    @param:Element(name = "cod_lesividad")
    val codLesividad: String,
    @field:Element(name = "lesividad")
    @param:Element(name = "lesividad")
    val lesividad: String,
    @field:Element(name = "coordenada_x")
    @param:Element(name = "coordenada_x")
    val coordenadaX: String,
    @field:Element(name = "coordenada_y")
    @param:Element(name = "coordenada_y")
    val coordenadaY: String,
    @field:Element(name = "positiva_alcohol")
    @param:Element(name = "positiva_alcohol")
    val positivaAlcohol: String,
    @field:Element(name = "positiva_droga")
    @param:Element(name = "positiva_droga")
    val positivaDroga: String
) {
    override fun toString(): String {
        return "AccidenteDTO(numExpediente='$numExpediente', fecha='$fecha', hora='$hora', localizacion='$localizacion', numero='$numero', codDistrito='$codDistrito', distrito='$distrito', tipoAccidente='$tipoAccidente', estadoMeteorologico='$estadoMeteorologico', tipoVehiculo='$tipoVehiculo', tipoPersona='$tipoPersona', rangoEdad='$rangoEdad', sexo='$sexo', codLesividad='$codLesividad', lesividad='$lesividad', coordenadaX='$coordenadaX', coordenadaY='$coordenadaY', positivaAlcohol='$positivaAlcohol', positivaDroga='$positivaDroga')"
    }
}
