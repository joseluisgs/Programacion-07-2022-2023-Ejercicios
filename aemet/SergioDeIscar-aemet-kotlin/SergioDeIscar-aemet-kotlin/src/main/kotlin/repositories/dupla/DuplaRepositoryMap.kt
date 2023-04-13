package repositories.dupla

import models.Dupla
import models.Informe
import mu.KotlinLogging
import services.storage.dupla.DuplaStorageService
import java.time.LocalDate
import java.time.LocalTime

private val logger = KotlinLogging.logger {  }

class DuplaRepositoryMap(
    private val storageService: DuplaStorageService
): DuplaRepository {
    private val duplas = mutableMapOf<String,Dupla>()

    override fun maxTemPorLugar(): Map<LocalDate, Map<String, Double>> {
        logger.debug { "DuplaRepositoryMap ->\tmaxTemPorLugar" }
        upgrade()
        return duplas.values
            .groupBy { it.day }
            .mapValues {
                it.value.groupBy { it.poblacion }
                    .mapValues {
                        it.value.maxBy { it.temMax }.temMax
                    }
            }
    }

    override fun minTemPorLugar(): Map<LocalDate, Map<String, Double>> {
        logger.debug { "DuplaRepositoryMap ->\tminTemPorLugar" }
        upgrade()
        return duplas.values
            .groupBy { it.day }
            .mapValues {
                it.value.groupBy { it.poblacion }
                    .mapValues {
                        it.value.minBy { it.temMin }.temMin
                    }
            }
    }

    override fun maxTemPorProvincia(): Map<String, Double> {
        logger.debug { "DuplaRepositoryMap ->\tmaxTemPorProvincia" }
        upgrade()
        return duplas.values
            .groupBy { it.provincia }
            .mapValues {
                it.value.maxBy { it.temMax }.temMax
            }
    }

    override fun minTemPorProvincia(): Map<String, Double> {
        logger.debug { "DuplaRepositoryMap ->\tminTemPorProvincia" }
        upgrade()
        return duplas.values
            .groupBy { it.provincia }
            .mapValues {
                it.value.minBy { it.temMin }.temMin
            }
    }

    override fun mediaPorProvincia(): Map<String, Double> {
        logger.debug { "DuplaRepositoryMap ->\tmediaPorProvincia" }
        upgrade()
        return duplas.values
            .groupBy { it.provincia }
            .mapValues {
                it.value.map {
                    it.temMax - it.temMin
                }.average()
            }
    }

    override fun mediaPrecipitacionPorDiaYProvincia(): Map<LocalDate, Map<String, Double>> {
        logger.debug { "DuplaRepositoryMap ->\tmediaPrecipitacionPorDiaYProvincia" }
        upgrade()
        return duplas.values
            .groupBy { it.day }
            .mapValues {
                it.value.groupBy { it.provincia }
                    .mapValues {
                        it.value.map { it.precipitacion }.average()
                    }
            }
    }

    override fun mediaTemMadrid(): Double {
        logger.debug { "DuplaRepositoryMap ->\tmediaTemMadrid" }
        upgrade()
        return duplas.values
            .filter { it.provincia == "Madrid" }
            .map { it.temMax - it.temMin }
            .average()
    }

    override fun mediaTemMax(): Double {
        logger.debug { "DuplaRepositoryMap ->\tmediaTemMax" }
        upgrade()
        return duplas.values
            .map { it.temMax }
            .average()
    }

    override fun mediaTemMin(): Double {
        logger.debug { "DuplaRepositoryMap ->\tmediaTemMin" }
        upgrade()
        return duplas.values
            .map { it.temMin }
            .average()
    }

    override fun maxTemAntes(): Map<LocalDate, List<String>> {
        logger.debug { "DuplaRepositoryMap ->\tmaxTemAntes" }
        upgrade()
        return duplas.values
            .groupBy { it.day }
            .mapValues {
                it.value.filter { it.timeMax.isBefore(LocalTime.of(15, 0)) }
                    .map { it.poblacion }
            }
    }

    override fun minTemDespues(): Map<LocalDate, List<String>> {
        logger.debug { "DuplaRepositoryMap ->\tminTemDespues" }
        upgrade()
        return duplas.values
            .groupBy { it.day }
            .mapValues {
                it.value.filter { it.timeMax.isAfter(LocalTime.of(17, 30)) }
                    .map { it.poblacion }
            }
    }

    override fun maxTemMadrid(day: LocalDate): Dupla {
        logger.debug { "DuplaRepositoryMap ->\tmaxTemMadrid" }
        upgrade()
        return duplas.values
            .filter { it.provincia == "Madrid" && it.day == day }
            .maxBy { it.temMax }
    }

    override fun minTemMadrid(day: LocalDate): Dupla {
        logger.debug { "DuplaRepositoryMap ->\tminTemMadrid" }
        upgrade()
        return duplas.values
            .filter { it.provincia == "Madrid" && it.day == day }
            .minBy { it.temMin }
    }

    override fun mediaPrecipitacionMadrid(day: LocalDate): Double {
        logger.debug { "DuplaRepositoryMap ->\tmediaPrecipitacionMadrid" }
        upgrade()
        return duplas.values
            .filter { it.provincia == "Madrid" && it.day == day }
            .map { it.precipitacion }
            .average()
    }

    override fun getAll(): List<Dupla> {
        logger.debug { "DuplaRepositoryMap ->\tgetAll" }
        upgrade()
        return duplas.values.toList()
    }

    override fun getById(id: String): Dupla? {
        logger.debug { "DuplaRepositoryMap ->\tgetById" }
        upgrade()
        return duplas.values.find { it.poblacion == id }
    }

    override fun save(element: Dupla, storage: Boolean): Dupla {
        logger.debug { "DuplaRepositoryMap ->\tsave" }
        duplas["${element.poblacion} ${element.day}"] = element
        if (storage) downgrade()
        return element
    }

    override fun saveAll(elements: List<Dupla>, storage: Boolean) {
        logger.debug { "DuplaRepositoryMap ->\tsaveAll" }
        elements.forEach { save(it, storage) }
    }

    override fun deleteById(id: String): Dupla? {
        logger.debug { "DuplaRepositoryMap ->\tdeleteById" }
        val dupla = getById(id) ?: return null
        duplas.remove("${dupla.poblacion} ${dupla.day}")
        downgrade()
        return dupla
    }

    override fun update(element: Dupla): Dupla? {
        logger.debug { "DuplaRepositoryMap ->\tupdate" }
        return updateById(element.poblacion, element)
    }

    override fun updateById(id: String, element: Dupla): Dupla? {
        logger.debug { "DuplaRepositoryMap ->\tupdateById" }
        deleteById(id) ?: return null
        return save(element)
    }

    override fun upgrade(): List<Dupla> {
        logger.debug { "DuplaRepositoryMap ->\tupgrade" }
        duplas.clear()
        val load = storageService.loadAll()
        saveAll(load, false)
        return load
    }

    override fun downgrade(): List<Dupla> {
        logger.debug { "DuplaRepositoryMap ->\tdowngrade" }
        return storageService.saveAll(duplas.values.toList())
    }
}