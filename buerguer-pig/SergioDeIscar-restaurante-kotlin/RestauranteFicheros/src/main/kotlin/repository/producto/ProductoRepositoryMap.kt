package repository.producto

import models.Bebida
import models.Hamburguesa
import models.Producto
import mu.KotlinLogging
import services.storage.productos.ProductosStorageService

private val logger = KotlinLogging.logger {}

class ProductoRepositoryMap(
    private val storageService: ProductosStorageService
): ProductoRepository {

    private val productos = mutableMapOf<Long, Producto>()

    override fun getOrderByPrecio(): List<Producto> {
        logger.debug { "ProductoRepositoryMap ->\tgetAllOrderByPrecio" }
        upgrade()
        return productos.values.sortedBy { it.precio }
    }

    override fun getProductoMasCaro(): Producto? {
        logger.debug { "ProductoRepositoryMap ->\tgetProductoMasCaro" }
        upgrade()
        return productos.values.maxByOrNull { it.precio }
    }

    override fun getProductoMasBarato(): Producto? {
        logger.debug { "ProductoRepositoryMap ->\tgetProductoMasBarato" }
        upgrade()
        return productos.values.minByOrNull { it.precio }
    }

    override fun getBebidaConMenosCapacidad(): Bebida? {
        logger.debug { "ProductoRepositoryMap ->\tgetBebidaConMenosCapacidad" }
        upgrade()
        return productos.values.filterIsInstance<Bebida>().minByOrNull { it.capacidad }
    }

    override fun getHamburguesaMasCara(): Hamburguesa? {
        logger.debug { "ProductoRepositoryMap ->\tgetHamburguesaMasCara" }
        upgrade()
        return productos.values.filterIsInstance<Hamburguesa>().maxByOrNull { it.precio }
    }

    override fun getHamburguesaConMasIngredientes(): Hamburguesa? {
        logger.debug { "ProductoRepositoryMap ->\tgetHamburguesaConMasIngredientes" }
        upgrade()
        return productos.values.filterIsInstance<Hamburguesa>().maxByOrNull { it.ingredientes.size }
    }

    override fun getHamburguesasPrecioMedio(): Double {
        logger.debug { "ProductoRepositoryMap ->\tgetHamburguesasPrecioMedio" }
        upgrade()
        return productos.values.filterIsInstance<Hamburguesa>().map { it.precio }.average()
    }

    override fun getPrecioMedioIngredientes(): Map<String, Double> {
        logger.debug { "ProductoRepositoryMap ->\tgetPrecioMedioIngredientes" }
        upgrade()
        return productos.values
            .filterIsInstance<Hamburguesa>()
            .flatMap { it.ingredientes }
            .groupBy { it.nombre }
            .mapValues { it.value.map { it.precio }.average() }
    }

    override fun findAll(): List<Producto> {
        logger.debug { "ProductoRepositoryMap ->\tfindAll" }
        upgrade()
        return productos.values.toList()
    }

    override fun findById(id: Long): Producto? {
        logger.debug { "ProductoRepositoryMap ->\tfindById" }
        upgrade()
        return productos[id]
    }

    override fun save(element: Producto, storage: Boolean): Producto {
        logger.debug { "ProductoRepositoryMap ->\tsave" }
        if (element.id < 0){ // Simula el comportamiento de un autoincremental
            element.id = productos.keys.maxOrNull()?.plus(1) ?: 1
        }
        productos[element.id] = element
        if (storage) downgrade()
        return element
    }

    override fun saveAll(elements: Iterable<Producto>, storage: Boolean) {
        logger.debug { "ProductoRepositoryMap ->\tsaveAll" }
        elements.forEach{ save(it, false) }
        if (storage) downgrade()
    }

    override fun deleteById(id: Long): Boolean {
        logger.debug { "Repositorio ->\tdeleteById: $id" }
        val result = productos.remove(id) != null
        downgrade()
        return result
    }

    override fun delete(element: Producto): Boolean {
        logger.debug { "Repositorio ->\tdelete: $element" }
        return deleteById(element.id)
    }

    override fun deleteAll() {
        logger.debug { "Repositorio ->\tdeleteAll" }
        productos.clear()
        downgrade()
    }

    override fun existsById(id: Long): Boolean {
        logger.debug { "Repositorio ->\texistsById: $id" }
        return findById(id) != null
    }

    private fun upgrade(): List<Producto> {
        logger.info { "Repositorio ->\tupgrade" }
        productos.clear()
        val load = storageService.loadAll()
        saveAll(load, false)
        return load
    }

    private fun downgrade(): List<Producto> {
        logger.info { "Repositorio ->\tdowngrade" }
        return storageService.saveAll(productos.values.toList())
    }
}