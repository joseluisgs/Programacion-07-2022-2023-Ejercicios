package model

import java.time.LocalDate
import java.time.LocalTime

data class Accidente (
    val numeroExpediente: String, // 0
    val fecha: LocalDate, // 1
    val hora: LocalTime, // 2
    val localizacion: List<String>, // 3
    val numeroCalle: Int?, // 4, en caso de que no sea válido pues un nulo
    val distrito: Distrito,
    val tipoAccidente: String, // 7
    val estadoMeteorologico: String, // 8
    val tipoDeVehiculo: String, // 9
    val tipoDePersona: String, // 10
    val rangoEdad: String, // 11
    val sexo: String, // 12
    val lesividad: Lesividad,
    val longitud: Double?, // 15
    val altitud: Double?, // 16
    val esPositivaEnAlchol: Boolean, // 17, N == false y S == true
    val esPositivaEnDrogas: Boolean // 18, NULL == false y 1 == true
)