package controllers

import models.Informe
import mu.KotlinLogging
import repositories.informe.InformeExtension
import repositories.informe.InformeRepository
import java.time.LocalDate

private val logger = KotlinLogging.logger {  }

class InformeController(
    private val informeRepository: InformeRepository
): InformeExtension {
    override fun getAll(): List<Informe> {
        logger.debug { "InformeController ->\tgetAll" }
        return informeRepository.getAll()
    }

    override fun getById(id: LocalDate): Informe? {
        logger.debug { "InformeController ->\tgetById" }
        return informeRepository.getById(id)
    }

    override fun save(element: Informe, storage: Boolean): Informe {
        logger.debug { "InformeController ->\tsave" }
        return informeRepository.save(element, storage)
    }

    override fun saveAll(elements: List<Informe>, storage: Boolean) {
        logger.debug { "InformeController ->\tsaveAll" }
        informeRepository.saveAll(elements, storage)
    }

    override fun deleteById(id: LocalDate): Informe? {
        logger.debug { "InformeController ->\tdeleteById" }
        return informeRepository.deleteById(id)
    }

    override fun update(element: Informe): Informe? {
        logger.debug { "InformeController ->\tupdate" }
        return informeRepository.update(element)
    }

    override fun updateById(id: LocalDate, element: Informe): Informe? {
        logger.debug { "InformeController ->\tupdateById" }
        return informeRepository.updateById(id, element)
    }
}