package repository.productos

import models.Producto
import mu.KotlinLogging
import services.storage.productos.ProductosStorageService

private val logger = KotlinLogging.logger {}

class ProductoRepositoryMap(
    private val storageService: ProductosStorageService
): ProductoRepositoryExternalStore {

    private val productos = mutableMapOf<Int, Producto>()

    override fun getAllOrderByPrecio(): List<Producto> {
        logger.debug { "Repositorio ->\tgetAllOrderByPrecio" }
        upgrade()
        return productos.values.sortedBy { it.precio }
    }

    override fun getAll(): List<Producto> {
        logger.debug { "Repositorio ->\tgetAll" }
        upgrade()
        return productos.values.toList()
    }

    override fun getById(id: Int): Producto? {
        logger.debug { "Repositorio ->\tfindById: $id" }
        upgrade()
        return productos[id]
    }

    override fun save(element: Producto): Producto {
        logger.debug { "Repositorio ->\tsave: $element" }
        productos[element.id] = element
        downgrade()
        return element
    }

    override fun saveAll(elements: List<Producto>) {
        logger.debug { "Repositorio ->\tsaveAll: ${elements.joinToString("\t")}" }
        elements.forEach{ save(it) }
    }

    override fun deleteById(id: Int): Producto? {
        logger.debug { "Repositorio ->\tdeleteById: $id" }
        val result = productos.remove(id)
        downgrade()
        return result
    }

    override fun update(element: Producto): Producto? {
        logger.debug { "Repositorio ->\tupdate: $element" }
        return updateById(element.id, element)
    }

    override fun updateById(id: Int, element: Producto): Producto? {
        logger.debug { "Repositorio ->\tupdateById: $id-$element" }
        if (getById(id) == null) return null
        productos[id] = element
        downgrade()
        return element
    }

    override fun upgrade(): List<Producto> {
        logger.info { "Repositorio ->\tupgrade" }
        productos.clear()
        val load = storageService.loadAll()
        saveAll(load)
        return load
    }

    override fun downgrade(): List<Producto> {
        logger.info { "Repositorio ->\tdowngrade" }
        return storageService.saveAll(productos.values.toList())
    }
}