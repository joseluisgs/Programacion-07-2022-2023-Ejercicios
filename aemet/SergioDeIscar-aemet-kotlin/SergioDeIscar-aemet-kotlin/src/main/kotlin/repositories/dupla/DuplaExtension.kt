package repositories.dupla

import models.Dupla
import models.Informe
import repositories.CrudRepository
import java.time.LocalDate

interface DuplaExtension: CrudRepository<Dupla, String> {
    fun maxTemPorLugar(): Map<LocalDate, Map<String, Double>>
    fun minTemPorLugar(): Map<LocalDate, Map<String, Double>>
    fun maxTemPorProvincia(): Map<String, Double>
    fun minTemPorProvincia(): Map<String, Double>
    fun mediaPorProvincia(): Map<String, Double>
    fun mediaPrecipitacionPorDiaYProvincia(): Map<LocalDate, Map<String, Double>>
    fun mediaTemMadrid(): Double
    fun mediaTemMax(): Double
    fun mediaTemMin(): Double

    /**
     * Lugares donde la máxima ha sido antes de las 15:00 por día
     */
    fun maxTemAntes(): Map<LocalDate, List<String>>

    /**
     * Lugares donde la mínima ha sido después de las 17:30 por día
     */
    fun minTemDespues(): Map<LocalDate, List<String>>

    fun maxTemMadrid(day: LocalDate): Dupla
    fun minTemMadrid(day: LocalDate): Dupla
    fun mediaPrecipitacionMadrid(day: LocalDate): Double
}