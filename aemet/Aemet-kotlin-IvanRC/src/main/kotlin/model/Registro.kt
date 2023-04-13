package model

import java.time.LocalDate
import java.time.LocalTime

data class Registro (
    val dia: LocalDate,
    val temperaturaMax: Double? = null,
    val temperaturaMin: Double? = null,
    val temperaturaMedia: Double? = null,
    val lugar: String? = null,
    val momento: LocalTime? = null,
    val huboPrecipitacion: String? = null,
    val precipitacion: Double? = null
)