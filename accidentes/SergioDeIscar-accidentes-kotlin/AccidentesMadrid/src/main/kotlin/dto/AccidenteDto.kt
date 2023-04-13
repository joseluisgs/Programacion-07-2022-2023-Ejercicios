package dto

import enums.TipoClima
import enums.TipoPersona
import enums.TipoSexo
import formateadores.*
import models.Accidente
import models.Coordenadas
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable
import java.time.LocalDateTime

@Root(name = "accidente")
data class AccidenteDto(
    @field:Attribute(name = "numExpediente")
    @param:Attribute(name = "numExpediente")
    val numExpediente: String,

    @field:Attribute(name = "fecha")
    @param:Attribute(name = "fecha")
    val fecha: String,

    @field:Attribute(name = "hora")
    @param:Attribute(name = "hora")
    val hora: String,

    @field:Element(name = "localizacion")
    @param:Element(name = "localizacion")
    val localizacion: String,

    @field:Element(name = "numero")
    @param:Element(name = "numero")
    val numero: String,

    @field:Element(name = "codDistrito")
    @param:Element(name = "codDistrito")
    val codDistrito: String,

    @field:Element(name = "distrito")
    @param:Element(name = "distrito")
    val distrito: String,

    @field:Element(name = "tipoAccidente")
    @param:Element(name = "tipoAccidente")
    val tipoAccidente: String,

    @field:Element(name = "clima")
    @param:Element(name = "clima")
    val clima: String,

    @field:Element(name = "tipoVehiculo")
    @param:Element(name = "tipoVehiculo")
    val tipoVehiculo: String,

    @field:Element(name = "tipoPersona")
    @param:Element(name = "tipoPersona")
    val tipoPersona: String,

    @field:Element(name = "rangoEdad")
    @param:Element(name = "rangoEdad")
    val rangoEdad: String,

    @field:Element(name = "sexo")
    @param:Element(name = "sexo")
    val sexo: String,

    @field:Element(name = "codLesividad")
    @param:Element(name = "codLesividad")
    val codLesividad : String,

    @field:Element(name = "lesividad")
    @param:Element(name = "lesividad")
    val lesividad: String,

    @field:Element(name = "coordenadaX")
    @param:Element(name = "coordenadaX")
    val coordenadaX: String,

    @field:Element(name = "coordenadaY")
    @param:Element(name = "coordenadaY")
    val coordenadaY: String,

    @field:Element(name = "alcochol")
    @param:Element(name = "alcochol")
    val alcochol: String,

    @field:Element(name = "drogas")
    @param:Element(name = "drogas")
    val drogas: String,
): Serializable {
    fun toClass() = Accidente(
        numExpediente = numExpediente,
        fechaYHora = fecha.toFechaYHora(hora),
        localizacion = localizacion,
        numero = numero,
        codDistrito = codDistrito.toIntOrNull() ?: -1,
        distrito = distrito,
        tipoAccidente = tipoAccidente,
        clima = clima.toClima(),
        tipoVehiculo = tipoVehiculo,
        tipoPersona = tipoPersona.toPersona(),
        rangoEdad = rangoEdad,
        sexo = sexo.toSexo(),
        codLesividad = codLesividad.toIntOrNull() ?: -1,
        lesividad = lesividad,
        coordenadas = Coordenadas(
            cordenadaX = coordenadaX.toCoordenada(),
            cordenadaY = coordenadaY.toCoordenada(),
        ),
        alcochol = alcochol == "S",
        drogas = drogas == "1",
    )
}