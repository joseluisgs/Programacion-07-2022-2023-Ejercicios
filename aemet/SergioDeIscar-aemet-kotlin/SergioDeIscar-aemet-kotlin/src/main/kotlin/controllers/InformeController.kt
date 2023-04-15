package controllers

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.onSuccess
import errors.InformeError
import models.Informe
import mu.KotlinLogging
import repositories.informe.InformeExtension
import repositories.informe.InformeRepository
import validators.validate
import java.time.LocalDate

private val logger = KotlinLogging.logger {  }

class InformeController(
    private val informeRepository: InformeRepository
) {
    fun getAll(): Iterable<Informe> {
        logger.debug { "InformeController ->\tgetAll" }
        return informeRepository.getAll()
    }

    fun getById(id: LocalDate): Result<Informe, InformeError> {
        logger.debug { "InformeController ->\tgetById" }
        return informeRepository.getById(id)?.let { Ok(it) } ?: Err(InformeError.InformeNoEncontradoError())
    }

    fun save(element: Informe, storage: Boolean = true): Result<Informe, InformeError> {
        logger.debug { "InformeController ->\tsave" }
        return element.validate().onSuccess { informeRepository.save(element, storage) }
    }

    fun saveAll(elements: Iterable<Informe>, storage: Boolean = true) {
        logger.debug { "InformeController ->\tsaveAll" }
        informeRepository.saveAll(elements, storage)
    }

    fun deleteById(id: LocalDate): Result<Informe, InformeError> {
        logger.debug { "InformeController ->\tdeleteById" }
        return informeRepository.deleteById(id)?.let { Ok(it) } ?: Err(InformeError.InformeNoEncontradoError())
    }

    fun update(element: Informe): Result<Informe, InformeError>{
        logger.debug { "InformeController ->\tupdate" }
        return element.validate().onSuccess { informeRepository.update(element) }
    }

    fun updateById(id: LocalDate, element: Informe): Result<Informe, InformeError> {
        logger.debug { "InformeController ->\tupdateById" }
        return element.validate().onSuccess { informeRepository.updateById(id, element) }
    }
}