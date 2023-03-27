package controllers

import models.Dupla
import mu.KotlinLogging
import repositories.dupla.DuplaExtension
import repositories.dupla.DuplaRepository
import java.time.LocalDate

private val logger = KotlinLogging.logger {  }

class DuplaController(
    private val repo: DuplaRepository
): DuplaExtension {
    override fun maxTemPorLugar(): Map<LocalDate, Map<String, Double>> {
        logger.debug { "DuplaController ->\tmaxTemPorLugar" }
        return repo.maxTemPorLugar()
    }

    override fun minTemPorLugar(): Map<LocalDate, Map<String, Double>> {
        logger.debug { "DuplaController ->\tminTemBroupPorLugar" }
        return repo.minTemPorLugar()
    }

    override fun maxTemPorProvincia(): Map<String, Double> {
        logger.debug { "DuplaController ->\tmaxTemPorProvincia" }
        return repo.maxTemPorProvincia()
    }

    override fun minTemPorProvincia(): Map<String, Double> {
        logger.debug { "DuplaController ->\tminTemPorProvincia" }
        return repo.minTemPorProvincia()
    }

    override fun mediaPorProvincia(): Map<String, Double> {
        logger.debug { "DuplaController ->\tmediaPorProvincia" }
        return repo.mediaPorProvincia()
    }

    override fun mediaPrecipitacionPorDiaYProvincia(): Map<LocalDate, Map<String, Double>> {
        logger.debug { "DuplaController ->\tmediaPrecipitacionPorDiaYProvincia" }
        return repo.mediaPrecipitacionPorDiaYProvincia()
    }

    override fun mediaTemMadrid(): Double {
        logger.debug { "DuplaController ->\tmediaTemMadrid" }
        return repo.mediaTemMadrid()
    }

    override fun mediaTemMax(): Double {
        logger.debug { "DuplaController ->\tmediaTemMax" }
        return repo.mediaTemMax()
    }

    override fun mediaTemMin(): Double {
        logger.debug { "DuplaController ->\tmediaTemMin" }
        return repo.mediaTemMin()
    }

    override fun maxTemAntes(): Map<LocalDate, List<String>> {
        logger.debug { "DuplaController ->\tmaxTemAntes" }
        return repo.maxTemAntes()
    }

    override fun minTemDespues(): Map<LocalDate, List<String>> {
        logger.debug { "DuplaController ->\tminTemDespues" }
        return repo.minTemDespues()
    }

    override fun maxTemMadrid(day: LocalDate): Dupla {
        logger.debug { "DuplaController ->\tmaxTemMadrid" }
        return repo.maxTemMadrid(day)
    }

    override fun minTemMadrid(day: LocalDate): Dupla {
        logger.debug { "DuplaController ->\tminTemMadrid" }
        return repo.minTemMadrid(day)
    }

    override fun mediaPrecipitacionMadrid(day: LocalDate): Double {
        logger.debug { "DuplaController ->\tmediaPrecipitacionMadrid" }
        return repo.mediaPrecipitacionMadrid(day)
    }

    override fun getAll(): List<Dupla> {
        logger.debug { "DuplaController ->\tgetAll" }
        return repo.getAll()
    }

    override fun getById(id: String): Dupla? {
        logger.debug { "DuplaController ->\tgetById" }
        return repo.getById(id)
    }

    override fun save(element: Dupla, storage: Boolean): Dupla {
        logger.debug { "DuplaController ->\tsave" }
        return repo.save(element, storage)
    }

    override fun saveAll(elements: List<Dupla>, storage: Boolean) {
        logger.debug { "DuplaController ->\tsaveAll" }
        repo.saveAll(elements, storage)
    }

    override fun deleteById(id: String): Dupla? {
        logger.debug { "DuplaController ->\tdeleteById" }
        return repo.deleteById(id)
    }

    override fun update(element: Dupla): Dupla? {
        logger.debug { "DuplaController ->\tupdate" }
        return repo.update(element)
    }

    override fun updateById(id: String, element: Dupla): Dupla? {
        logger.debug { "DuplaController ->\tupdateById" }
        return repo.updateById(id, element)
    }
}