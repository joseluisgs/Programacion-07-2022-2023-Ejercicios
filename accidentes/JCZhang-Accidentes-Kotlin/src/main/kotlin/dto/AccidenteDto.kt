package dto

import models.Accidente
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


@Root(name = "accidente")
data class AccidenteDto(

    @field: Attribute(name = "numExpediente")
    @param: Attribute(name = "numExpediente")
    val numeroExpediente: String,

    @field: Attribute(name = "fecha")
    @param: Attribute(name = "fecha")
    val fecha: String,

    @field: Attribute(name = "hora")
    @param: Attribute(name = "hora")
    val hora: String,

    @field: Element(name = "localizacion")
    @param: Element(name = "localizacion")
    val localizacion: String,

    @field: Element(name = "numero")
    @param: Element(name = "numero")
    val numero: String,

    @field: Element(name = "codDistrito")
    @param: Element(name = "codDistrito")
    val cod_distrito: String,

    @field: Element(name = "distrito")
    @param: Element(name = "distrito")
    val distrito: String,

    @field: Element(name = "tipoAccidente")
    @param: Element(name = "tipoAccidente")
    val tipoAccidente: String,

    @field: Element(name = "estadoMeteorologico")
    @param: Element(name = "estadoMeteorologico")
    val estadoMeteorologico: String,

    @field: Element(name = "tipoVehiculo")
    @param: Element(name = "tipoVehiculo")
    val tipoVehiculo: String,

    @field: Element(name = "tipoPersona")
    @param: Element(name = "tipoPersona")
    val tipoPersona: String,

    @field: Element(name = "randoEdad")
    @param: Element(name = "randoEdad")
    val rangoEdad: String,

    @field: Element(name = "sexo")
    @param: Element(name = "sexo")
    val sexo: String,

    @field: Element(name = "codLesividad")
    @param: Element(name = "codLesividad")
    val codLesividad: String,

    @field: Element(name = "lesividad")
    @param: Element(name = "lesividad")
    val lesividad: String,

    @field: Element(name = "coordenadaX")
    @param: Element(name = "coordenadaX")
    val coordenadaX: String,

    @field: Element(name = "coordenadaY")
    @param: Element(name = "coordenadaY")
    val coordenadaY: String,

    @field: Element(name = "positivoAlcohol")
    @param: Element(name = "positivoAlcohol")
    val positivoAlcohol: String,

    @field: Element(name = "positividadDroga")
    @param: Element(name = "positividadDroga")
    val positividadDroga: String,
    )

@Root(name = "accidentes")
data class AccidenteListDto(
    @field: ElementList(name = "accidentes", inline = true)
    @param: ElementList(name = "accidentes", inline = true)
    val accidentes: List<AccidenteDto>
)