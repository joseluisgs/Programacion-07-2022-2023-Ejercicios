package controller

import models.Pedido
import mu.KotlinLogging
import repository.pedido.PedidoRepository
import repository.pedido.PedidoRepositoryExternalStore

private val logger = KotlinLogging.logger {}

class PedidoController(
    private val repo: PedidoRepositoryExternalStore
): PedidoRepository {
    override fun getAllOrderByFecha(): List<Pedido> {
        logger.debug { "Controller ->\tgetAllOrderByFecha" }
        return repo.getAllOrderByFecha()
    }

    override fun getAll(): List<Pedido> {
        logger.debug { "Controller ->\tgetAll" }
        return repo.getAll()
    }

    override fun getById(id: Int): Pedido? {
        logger.debug { "Controller ->\tfindById: $id" }
        checkId(id)
        return repo.getById(id)
    }

    override fun save(element: Pedido): Pedido {
        logger.debug { "Controller ->\tsave: $element" }
        return repo.save(element)
    }

    override fun saveAll(elements: List<Pedido>) {
        logger.debug { "Controller ->\tsaveAll: ${elements.joinToString("\t")}" }
        elements.forEach { checkId(it.userId) }
        repo.saveAll(elements)
    }

    override fun deleteById(id: Int): Pedido? {
        logger.debug { "Controller ->\tdeleteById: $id" }
        checkId(id)
        return repo.deleteById(id)
    }

    override fun update(element: Pedido): Pedido? {
        logger.debug { "Controller ->\tupdate: $element" }
        checkId(element.userId)
        return repo.update(element)
    }

    override fun updateById(id: Int, element: Pedido): Pedido? {
        logger.debug { "Controller ->\tupdateById: $id-$element" }
        checkId(id)
        checkId(element.userId)
        return repo.updateById(id, element)
    }

    private fun checkId(id: Int) {
        logger.debug { "Controller ->\tcheckId: $id" }
        if (id < 0) throw IllegalArgumentException("Id debe ser mayor que 0")
    }
}