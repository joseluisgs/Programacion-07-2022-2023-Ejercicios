package models

import java.time.LocalDate
import java.time.LocalTime

data class Accidente (
    val numExpediente: String,
    val fecha: LocalDate,
    val hora: LocalTime,
    val calle: String,
    val numero: String,
    val codDistrito: String,
    val distrito: String,
    val tipoAccidente: String,
    val estadoMeteorologico: String,
    val tipoVehiculo: String,
    val tipoPersona: String,
    val rangoEdad: String,
    val sexo: String,
    val codLesividad: String,
    val lesividad: String,
    val coordenadaX: String,
    val coordenadaY: String,
    val positividadAlcohol: String,
    val positividadDroga: String
)