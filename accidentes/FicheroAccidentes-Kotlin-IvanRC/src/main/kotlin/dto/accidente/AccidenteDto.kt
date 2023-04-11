package dto.accidente

import dto.distrito.DistritoDto
import dto.lesividad.LesividadDto
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "accidente")
data class AccidenteDto (
    @field:Element(name = "numero_de_expediente")
    @param:Element(name = "numero_de_expediente")
    val numeroExpediente: String, // 0

    @field:Element(name = "fecha")
    @param:Element(name = "fecha")
    val fecha: String, // 1

    @field:Element(name = "hora")
    @param:Element(name = "hora")
    val hora: String, // 2

    @field:ElementList(name = "localizacion", inline = true)
    @param:ElementList(name = "localizacion", inline = true)
    val localizacion: List<String>, // 3

    @field:Element(name = "numero_calle")
    @param:Element(name = "numero_calle")
    val numeroCalle: String, // 4, en caso de que no sea válido pues un nulo

    @field:Element(name = "distrito")
    @param:Element(name = "distrito")
    val distrito: DistritoDto,

    @field:Element(name = "tipo_de_accidente")
    @param:Element(name = "tipo_de_accidente")
    val tipoAccidente: String, // 7

    @field:Element(name = "estado_meteorologico")
    @param:Element(name = "estado_meteorologico")
    val estadoMeteorologico: String, // 8

    @field:Element(name = "tipo_de_vehiculo")
    @param:Element(name = "tipo_de_vehiculo")
    val tipoDeVehiculo: String, // 9

    @field:Element(name = "tipo_de_persona")
    @param:Element(name = "tipo_de_persona")
    val tipoDePersona: String, // 10

    @field:Element(name = "rango_de_edad")
    @param:Element(name = "rango_de_edad")
    val rangoEdad: String, // 11

    @field:Element(name = "sexo")
    @param:Element(name = "sexo")
    val sexo: String, // 12

    @field:Element(name = "lesividad")
    @param:Element(name = "lesividad")
    val lesividad: LesividadDto,

    @field:Element(name = "longitud")
    @param:Element(name = "longitud")
    val longitud: String, // 15

    @field:Element(name = "altitud")
    @param:Element(name = "altitud")
    val altitud: String, // 16

    @field:Element(name = "es_positiva_en_alchol")
    @param:Element(name = "es_positiva_en_alchol")
    val esPositivaEnAlchol: String, // 17, N == false y S == true

    @field:Element(name = "es_positiva_en_drogas")
    @param:Element(name = "es_positiva_en_drogas")
    val esPositivaEnDrogas: String // 18, NULL == false y 1 == true
)