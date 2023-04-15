package controller

import models.Producto
import mu.KotlinLogging
import repository.productos.ProductoRepository
import repository.productos.ProductoRepositoryExternalStore

private val logger = KotlinLogging.logger {  }

class ProductoController(
    private val repo: ProductoRepositoryExternalStore
): ProductoRepository {
    override fun getAllOrderByPrecio(): List<Producto> {
        logger.debug { "Controller ->\tgetAllOrderByPrecio" }
        return repo.getAllOrderByPrecio()
    }

    override fun getAll(): List<Producto> {
        logger.debug { "Controller ->\tgetAll" }
        return repo.getAll()
    }

    override fun getById(id: Int): Producto? {
        logger.debug { "Controller ->\tfindById: $id" }
        checkId(id)
        return repo.getById(id)
    }

    override fun save(element: Producto): Producto {
        logger.debug { "Controller ->\tsave: $element" }
        checkId(element.id)
        return repo.save(element)
    }

    override fun saveAll(elements: List<Producto>) {
        logger.debug { "Controller ->\tsaveAll: ${elements.joinToString("\t")}" }
        elements.forEach { checkId(it.id) }
        return repo.saveAll(elements)
    }

    override fun deleteById(id: Int): Producto? {
        logger.debug { "Controller ->\tdeleteById: $id" }
        checkId(id)
        return repo.deleteById(id)
    }

    override fun update(element: Producto): Producto? {
        logger.debug { "Controller ->\tupdate: $element" }
        checkId(element.id)
        return repo.update(element)
    }

    override fun updateById(id: Int, element: Producto): Producto? {
        logger.debug { "Controller ->\tupdateById: $id-$element" }
        checkId(id)
        checkId(element.id)
        return repo.updateById(id, element)
    }

    private fun checkId(id: Int) {
        if (id < 0) throw IllegalArgumentException("Id debe ser mayor que 0")
    }
}