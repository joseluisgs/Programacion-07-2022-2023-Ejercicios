package model

import java.time.LocalDate
import java.time.LocalTime

data class ObjetoDeExposicion (
    val dia: LocalDate,
    val lugar: String,
    val valor: Double,
    val momento: LocalTime? = null
)