package controller

import models.Hamburguesa
import mu.KotlinLogging
import repository.CrudRepository
import repository.hamburguesa.HamburguesaRepository
import repository.hamburguesa.HamburguesaRepositoryExternalStore

private val logger = KotlinLogging.logger {}

class HamburguesaController(
    private val repo: HamburguesaRepositoryExternalStore
): HamburguesaRepository {
    override fun getAllOrderByPrecio(): List<Hamburguesa> {
        logger.debug { "Controller ->\tgetAllOrderByPrecio" }
        return repo.getAllOrderByPrecio()
    }

    override fun findByNombre(nombre: String): Hamburguesa? {
        logger.debug { "Controller ->\tfindByNombre: $nombre" }
        return repo.findByNombre(nombre)
    }

    override fun getHamburguesaMasCara(): Hamburguesa? {
        logger.debug { "Controller ->\tgetHamburguesaMasCara" }
        return repo.getHamburguesaMasCara()
    }

    override fun getHamburguesaConMasIngredientes(): Hamburguesa? {
        logger.debug { "Controller ->\tgetHamburguesaConMasIngredientes" }
        return repo.getHamburguesaConMasIngredientes()
    }

    override fun getPrecioMedio(): Double {
        logger.debug { "Controller ->\tgetPrecioMedio" }
        return repo.getPrecioMedio()
    }

    override fun getPrecioMedioIngredientes(): Map<String, Double> {
        logger.debug { "Controller ->\tgetPrecioMedioIngredientes" }
        return repo.getPrecioMedioIngredientes()
    }

    override fun getAll(): List<Hamburguesa> {
        return repo.getAll()
    }

    override fun getById(id: Int): Hamburguesa? {
        logger.debug { "Controller ->\tfindById: $id" }
        checkId(id)
        return repo.getById(id)
    }

    override fun save(element: Hamburguesa): Hamburguesa {
        logger.debug { "Controller ->\tsave: $element" }
        checkId(element.id)
        return repo.save(element)
    }

    override fun saveAll(elements: List<Hamburguesa>) {
        logger.debug { "Controller ->\tsaveAll: ${elements.joinToString("\t")}" }
        return repo.saveAll(elements)
    }

    override fun deleteById(id: Int): Hamburguesa? {
        logger.debug { "Controller ->\tdeleteById: $id" }
        checkId(id)
        return repo.deleteById(id)
    }

    override fun update(element: Hamburguesa): Hamburguesa? {
        logger.debug { "Controller ->\tupdate: $element" }
        return repo.update(element)
    }

    override fun updateById(id: Int, element: Hamburguesa): Hamburguesa? {
        logger.debug { "Controller ->\tupdateById: $id-$element" }
        checkId(id)
        return repo.updateById(id, element)
    }

    private fun checkId(id: Int) {
        logger.debug { "Controller ->\tcheckId: $id" }
        if (id < 0) throw IllegalArgumentException("El id no puede ser negativo")
    }
}