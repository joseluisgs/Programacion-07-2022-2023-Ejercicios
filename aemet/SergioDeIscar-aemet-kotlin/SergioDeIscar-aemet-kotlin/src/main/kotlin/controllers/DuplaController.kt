package controllers

import com.github.michaelbull.result.*
import errors.DuplaError
import models.Dupla
import mu.KotlinLogging
import repositories.dupla.DuplaExtension
import repositories.dupla.DuplaRepository
import validators.validate
import java.time.LocalDate

private val logger = KotlinLogging.logger {  }

class DuplaController(
    private val repo: DuplaRepository
) {
    fun maxTemPorLugar(): Map<LocalDate, Map<String, Double>> {
        logger.debug { "DuplaController ->\tmaxTemPorLugar" }
        return repo.maxTemPorLugar()
    }

    fun minTemPorLugar(): Map<LocalDate, Map<String, Double>> {
        logger.debug { "DuplaController ->\tminTemBroupPorLugar" }
        return repo.minTemPorLugar()
    }

    fun maxTemPorProvincia(): Map<String, Double> {
        logger.debug { "DuplaController ->\tmaxTemPorProvincia" }
        return repo.maxTemPorProvincia()
    }

    fun minTemPorProvincia(): Map<String, Double> {
        logger.debug { "DuplaController ->\tminTemPorProvincia" }
        return repo.minTemPorProvincia()
    }

    fun mediaPorProvincia(): Map<String, Double> {
        logger.debug { "DuplaController ->\tmediaPorProvincia" }
        return repo.mediaPorProvincia()
    }

    fun mediaPrecipitacionPorDiaYProvincia(): Map<LocalDate, Map<String, Double>> {
        logger.debug { "DuplaController ->\tmediaPrecipitacionPorDiaYProvincia" }
        return repo.mediaPrecipitacionPorDiaYProvincia()
    }

    fun mediaTemMadrid(): Double {
        logger.debug { "DuplaController ->\tmediaTemMadrid" }
        return repo.mediaTemMadrid()
    }

    fun mediaTemMax(): Double {
        logger.debug { "DuplaController ->\tmediaTemMax" }
        return repo.mediaTemMax()
    }

    fun mediaTemMin(): Double {
        logger.debug { "DuplaController ->\tmediaTemMin" }
        return repo.mediaTemMin()
    }

    fun maxTemAntes(): Map<LocalDate, List<String>> {
        logger.debug { "DuplaController ->\tmaxTemAntes" }
        return repo.maxTemAntes()
    }

    fun minTemDespues(): Map<LocalDate, List<String>> {
        logger.debug { "DuplaController ->\tminTemDespues" }
        return repo.minTemDespues()
    }

    fun maxTemMadrid(day: LocalDate): Dupla {
        logger.debug { "DuplaController ->\tmaxTemMadrid" }
        return repo.maxTemMadrid(day)
    }

    fun minTemMadrid(day: LocalDate): Dupla {
        logger.debug { "DuplaController ->\tminTemMadrid" }
        return repo.minTemMadrid(day)
    }

    fun mediaPrecipitacionMadrid(day: LocalDate): Double {
        logger.debug { "DuplaController ->\tmediaPrecipitacionMadrid" }
        return repo.mediaPrecipitacionMadrid(day)
    }

    fun getAll(): Iterable<Dupla> {
        logger.debug { "DuplaController ->\tgetAll" }
        return repo.getAll()
    }

    fun getById(id: String): Result<Dupla, DuplaError> {
        logger.debug { "DuplaController ->\tgetById" }
        return repo.getById(id)?.let { Ok(it) } ?: Err(DuplaError.DuplaNoEncontradaError())
    }

    fun save(element: Dupla, storage: Boolean = true): Result<Dupla, DuplaError> {
        logger.debug { "DuplaController ->\tsave" }
        return element.validate().onSuccess { repo.save(element, storage) }
    }

    fun saveAll(elements: Iterable<Dupla>, storage: Boolean = true) {
        logger.debug { "DuplaController ->\tsaveAll" }
        repo.saveAll(elements, storage)
    }

    fun deleteById(id: String): Result<Dupla, DuplaError> {
        logger.debug { "DuplaController ->\tdeleteById" }
        return repo.deleteById(id)?.let { Ok(it) } ?: Err(DuplaError.DuplaNoEncontradaError())
    }

    fun update(element: Dupla): Result<Dupla, DuplaError> {
        logger.debug { "DuplaController ->\tupdate" }
        return element.validate().andThen { repo.update(element)?.let { Ok(it) } ?: Err(DuplaError.DuplaNoEncontradaError()) }
    }

    fun updateById(id: String, element: Dupla): Result<Dupla, DuplaError> {
        logger.debug { "DuplaController ->\tupdateById" }
        return element.validate().andThen { repo.updateById(id, element)?.let { Ok(it) } ?: Err(DuplaError.DuplaNoEncontradaError()) }
    }
}