package repository.producto

import models.Bebida
import models.Hamburguesa
import models.Producto
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

object ProductoRepositoryMap: ProductoRepository {

    private val productos = mutableMapOf<Long, Producto>()

    override fun getOrderByPrecio(): List<Producto> {
        logger.debug { "ProductoRepositoryMap ->\tgetAllOrderByPrecio" }
        return productos.values.sortedBy { it.precio }
    }

    override fun getProductoMasCaro(): Producto? {
        logger.debug { "ProductoRepositoryMap ->\tgetProductoMasCaro" }
        return productos.values.maxByOrNull { it.precio }
    }

    override fun getProductoMasBarato(): Producto? {
        logger.debug { "ProductoRepositoryMap ->\tgetProductoMasBarato" }
        return productos.values.minByOrNull { it.precio }
    }

    override fun getBebidaConMenosCapacidad(): Bebida? {
        logger.debug { "ProductoRepositoryMap ->\tgetBebidaConMenosCapacidad" }
        return productos.values.filterIsInstance<Bebida>().minByOrNull { it.capacidad }
    }

    override fun getHamburguesaMasCara(): Hamburguesa? {
        logger.debug { "ProductoRepositoryMap ->\tgetHamburguesaMasCara" }
        return productos.values.filterIsInstance<Hamburguesa>().maxByOrNull { it.precio }
    }

    override fun getHamburguesaConMasIngredientes(): Hamburguesa? {
        logger.debug { "ProductoRepositoryMap ->\tgetHamburguesaConMasIngredientes" }
        return productos.values.filterIsInstance<Hamburguesa>().maxByOrNull { it.ingredientes.size }
    }

    override fun getHamburguesasPrecioMedio(): Double {
        logger.debug { "ProductoRepositoryMap ->\tgetHamburguesasPrecioMedio" }
        return productos.values.filterIsInstance<Hamburguesa>().map { it.precio }.average()
    }

    override fun getPrecioMedioIngredientes(): Map<String, Double> {
        logger.debug { "ProductoRepositoryMap ->\tgetPrecioMedioIngredientes" }
        return productos.values
            .filterIsInstance<Hamburguesa>()
            .flatMap { it.ingredientes }
            .groupBy { it.nombre }
            .mapValues { it.value.map { it.precio }.average() }
    }

    override fun findAll(): List<Producto> {
        logger.debug { "ProductoRepositoryMap ->\tfindAll" }
        return productos.values.toList()
    }

    override fun findById(id: Long): Producto? {
        logger.debug { "ProductoRepositoryMap ->\tfindById" }
        return productos[id]
    }

    override fun save(element: Producto): Producto {
        logger.debug { "ProductoRepositoryMap ->\tsave" }
        if (element.id <= 0){ // Simula el comportamiento de un autoincremental
            element.id = productos.keys.maxOrNull()?.plus(1) ?: 1
        }
        productos[element.id] = element
        return element
    }

    override fun saveAll(elements: Iterable<Producto>) {
        logger.debug { "ProductoRepositoryMap ->\tsaveAll" }
        elements.forEach{ save(it) }
    }

    override fun deleteById(id: Long): Boolean {
        logger.debug { "Repositorio ->\tdeleteById: $id" }
        return productos.remove(id) != null
    }

    override fun delete(element: Producto): Boolean {
        logger.debug { "Repositorio ->\tdelete: $element" }
        return deleteById(element.id)
    }

    override fun deleteAll() {
        logger.debug { "Repositorio ->\tdeleteAll" }
        productos.clear()
    }

    override fun existsById(id: Long): Boolean {
        logger.debug { "Repositorio ->\texistsById: $id" }
        return findById(id) != null
    }
}