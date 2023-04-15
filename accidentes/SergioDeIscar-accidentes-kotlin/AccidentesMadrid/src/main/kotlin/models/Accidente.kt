package models

import dto.AccidenteDto
import enums.TipoClima
import enums.TipoPersona
import enums.TipoSexo
import formateadores.toFormatTime
import java.io.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Accidente(
    val numExpediente: String,
    // Junta la fecha y la hora en un solo campo
    val fechaYHora: LocalDateTime,
    val localizacion: String,
    val numero: String,
    val codDistrito: Int,
    val distrito: String,
    val tipoAccidente: String,
    val clima: TipoClima,
    val tipoVehiculo: String,
    val tipoPersona: TipoPersona,
    val rangoEdad: String,
    val sexo: TipoSexo,
    val codLesividad : Int?,
    val lesividad: String?,
    val coordenadas: Coordenadas,
    val alcochol: Boolean,
    val drogas: Boolean,
): Serializable{
    fun toDto() = AccidenteDto(
        numExpediente = numExpediente,
        fecha = fechaYHora.toLocalDate().toString(),
        hora = fechaYHora.toLocalTime().toFormatTime(),
        localizacion = localizacion,
        numero = numero,
        codDistrito = codDistrito.toString(),
        distrito = distrito,
        tipoAccidente = tipoAccidente,
        clima = clima.toString(),
        tipoVehiculo = tipoVehiculo,
        tipoPersona = tipoPersona.toString(),
        rangoEdad = rangoEdad,
        sexo = sexo.toString(),
        codLesividad = codLesividad.toString(),
        lesividad = lesividad ?: "NULL",
        coordenadaX = coordenadas.cordenadaX.toString(),
        coordenadaY = coordenadas.cordenadaY.toString(),
        alcochol = alcochol.toString(),
        drogas = drogas.toString(),
    )
}