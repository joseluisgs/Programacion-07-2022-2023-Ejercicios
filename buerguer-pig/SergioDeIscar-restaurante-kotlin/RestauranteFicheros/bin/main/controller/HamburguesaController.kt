package controller

import models.Hamburguesa
import mu.KotlinLogging
import repository.hamburguesa.HamburguesaRepository

private val logger = KotlinLogging.logger {}

class HamburguesaController(
    private val repo: HamburguesaRepository
) {
    fun getAllOrderByPrecio(): List<Hamburguesa> {
        logger.debug { "Controller ->\tgetAllOrderByPrecio" }
        return repo.getAllOrderByPrecio()
    }

    fun findByNombre(nombre: String): Hamburguesa? {
        logger.debug { "Controller ->\tfindByNombre: $nombre" }
        return repo.findByNombre(nombre)
    }

    fun getAll(): List<Hamburguesa> {
        return repo.getAll()
    }

    fun getById(id: Int): Hamburguesa? {
        logger.debug { "Controller ->\tfindById: $id" }
        checkId(id)
        return repo.getById(id)
    }

    fun save(element: Hamburguesa): Hamburguesa {
        logger.debug { "Controller ->\tsave: $element" }
        checkId(element.id)
        return repo.save(element)
    }

    fun saveAll(elements: List<Hamburguesa>) {
        logger.debug { "Controller ->\tsaveAll: ${elements.joinToString("\t")}" }
        return repo.saveAll(elements)
    }

    fun deleteById(id: Int): Hamburguesa? {
        logger.debug { "Controller ->\tdeleteById: $id" }
        checkId(id)
        return repo.deleteById(id)
    }

    fun update(element: Hamburguesa): Hamburguesa? {
        logger.debug { "Controller ->\tupdate: $element" }
        return repo.update(element)
    }

    fun updateById(id: Int, element: Hamburguesa): Hamburguesa? {
        logger.debug { "Controller ->\tupdateById: $id-$element" }
        checkId(id)
        return repo.updateById(id, element)
    }

    private fun checkId(id: Int) {
        logger.debug { "Controller ->\tcheckId: $id" }
        if (id < 0) throw IllegalArgumentException("El id no puede ser negativo")
    }
}