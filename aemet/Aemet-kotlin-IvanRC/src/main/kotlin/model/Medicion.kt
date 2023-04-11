package model

import java.time.LocalDate
import java.time.LocalTime

data class Medicion (
    val poblacion: String,
    val provincia: String,
    val temperaturaMax: Double,
    val horaTempMax: LocalTime,
    val temperaturaMin: Double,
    val horaTempMin: LocalTime,
    val precipitacion: Double,
    val dia: LocalDate
)