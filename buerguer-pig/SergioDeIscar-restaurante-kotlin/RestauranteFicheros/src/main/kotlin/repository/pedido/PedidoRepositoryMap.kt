package repository.pedido

import models.Pedido
import mu.KotlinLogging
import services.storage.pedido.PedidoStorageService

private val logger = KotlinLogging.logger {}

class PedidoRepositoryMap(
    private val storageService: PedidoStorageService
): PedidoRepositoryExternalStore {
    private val pedidos = mutableMapOf<Int, Pedido>()
    override fun getAllOrderByFecha(): List<Pedido> {
        logger.debug { "Repositorio ->\tgetAllOrderByFecha" }
        upgrade()
        return pedidos.values.sortedBy { it.createAt }
    }

    override fun getAll(): List<Pedido> {
        logger.debug { "Repositorio ->\tgetAll" }
        upgrade()
        return pedidos.values.toList()
    }

    override fun getById(id: Int): Pedido? {
        logger.debug { "Repositorio ->\tfindById: $id" }
        upgrade()
        return pedidos[id]
    }

    override fun save(element: Pedido): Pedido {
        logger.debug { "Repositorio ->\tsave: $element" }
        pedidos[element.userId] = element
        downgrade()
        return element
    }

    override fun saveAll(elements: List<Pedido>) {
        logger.debug { "Repositorio ->\tsaveAll: ${elements.joinToString("\t")}" }
        elements.forEach{ save(it) }
    }

    override fun deleteById(id: Int): Pedido? {
        logger.debug { "Repositorio ->\tdeleteById: $id" }
        val result = pedidos.remove(id)
        downgrade()
        return result
    }

    override fun update(element: Pedido): Pedido? {
        logger.debug { "Repositorio ->\tupdate: $element" }

        return updateById(element.userId, element)
    }

    override fun updateById(id: Int, element: Pedido): Pedido? {
        logger.debug { "Repositorio ->\tupdateById: $id-$element" }

        if (getById(id) == null) return null
        pedidos[id] = element

        downgrade()

        return element
    }

    override fun upgrade(): List<Pedido> {
        logger.info { "Repositorio ->\tupgrade" }

        pedidos.clear()

        val load = storageService.loadAll()

        saveAll(load)

        return load
    }

    override fun downgrade(): List<Pedido> {
        logger.info { "Repositorio ->\tdowngrade" }

        return storageService.saveAll(pedidos.values.toList())
    }
}