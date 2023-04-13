package repositories.informe

import models.Informe
import mu.KotlinLogging
import services.storage.informe.InformeStorageService
import java.time.LocalDate

private val logger = KotlinLogging.logger {  }

class InformeRepositoryMap(
    private val storageService: InformeStorageService
): InformeRepository {
    private val informes = mutableMapOf<LocalDate,Informe>()

    override fun getAll(): List<Informe> {
        logger.debug { "InformeRepositoryMap ->\tgetAll" }
        return informes.values.toList()
    }

    override fun getById(id: LocalDate): Informe? {
        logger.debug { "InformeRepositoryMap ->\tgetById" }
        return informes[id]
    }

    override fun save(element: Informe, storage: Boolean): Informe {
        logger.debug { "InformeRepositoryMap ->\tsave" }
        informes[element.day] = element
        if (storage) downgrade()
        return element
    }

    override fun saveAll(elements: List<Informe>, storage: Boolean) {
        logger.debug { "InformeRepositoryMap ->\tsaveAll" }
        elements.forEach { save(it, storage) }
    }

    override fun deleteById(id: LocalDate): Informe? {
        logger.debug { "InformeRepositoryMap ->\tdeleteById" }
        return informes.remove(id)
    }

    override fun update(element: Informe): Informe? {
        logger.debug { "InformeRepositoryMap ->\tupdate" }
        return updateById(element.day, element)
    }

    override fun updateById(id: LocalDate, element: Informe): Informe? {
        logger.debug { "InformeRepositoryMap ->\tupdateById" }
        if (getById(id) == null) return null
        informes[id] = element
        downgrade()
        return element
    }

    override fun upgrade(): List<Informe> {
        logger.debug { "InformeRepositoryMap ->\tupgrade" }
        informes.clear()
        val load = storageService.loadAll()
        saveAll(load, false)
        return load
    }

    override fun downgrade(): List<Informe> {
        logger.debug { "InformeRepositoryMap ->\tdowngrade" }
        return storageService.saveAll(informes.values.toList())
    }
}