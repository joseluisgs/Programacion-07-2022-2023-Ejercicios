package models

import dto.InformeDto
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

data class Informe(
    val day: LocalDate,
    val temMedia: Double,
    val temMax: Triple<String, LocalTime, Double>,
    val temMin: Triple<String, LocalTime, Double>,
    val precipitacion: Pair<Boolean,Double>
): Serializable