package repository.hamburguesa

import models.Hamburguesa
import mu.KotlinLogging
import services.storage.hamburguesa.HamburguesaStorageService

private val logger = KotlinLogging.logger {}

class HamburguesaRepositoryMap(
    private val storageService: HamburguesaStorageService
): HamburguesaRepositoryExternalStore {
    private val almacen = mutableMapOf<Int, Hamburguesa>()

    override fun getAllOrderByPrecio(): List<Hamburguesa> {
        logger.debug { "Repositorio ->\tgetAllOrderByPrecio" }

        upgrade()

        return almacen.values.sortedBy { it.precio }
    }

    override fun findByNombre(nombre: String): Hamburguesa? {
        logger.debug { "Repositorio ->\tfindByNombre: $nombre" }

        upgrade()

        return almacen.values.find { it.nombre.lowercase() == nombre.lowercase() }
    }

    override fun getHamburguesaMasCara(): Hamburguesa? {
        logger.debug { "Repositorio ->\tgetHamburguesaMasCara" }

        upgrade()

        return almacen.values.maxByOrNull { it.precio }
    }

    override fun getHamburguesaConMasIngredientes(): Hamburguesa? {
        logger.debug { "Repositorio ->\tgetHamburguesaConMasIngredientes" }

        upgrade()

        return almacen.values.maxByOrNull { it.ingredientes.size }
    }

    override fun getPrecioMedio(): Double {
        logger.debug { "Repositorio ->\tgetPrecioMedio" }

        upgrade()

        return almacen.values.map { it.precio }.average()
    }

    override fun getPrecioMedioIngredientes(): Map<String, Double> {
        logger.debug { "Repositorio ->\tgetPrecioMedioIngredientes" }

        upgrade()

        return almacen.values
            .flatMap { it.ingredientes }
            .groupBy { it.nombre }
            .mapValues { it.value.map { it.precio }.average() }
    }

    override fun getAll(): List<Hamburguesa> {
        logger.debug { "Repositorio ->\tfindAll" }

        upgrade()

        return almacen.values.toList()
    }

    override fun getById(id: Int): Hamburguesa? {
        logger.debug { "Repositorio ->\tfindById: $id" }

        upgrade()

        return almacen[id]
    }

    override fun save(element: Hamburguesa): Hamburguesa {
        logger.debug { "Repositorio ->\tsave: $element" }

        almacen[element.id] = element

        downgrade()

        return element
    }

    override fun saveAll(elements: List<Hamburguesa>) {
        logger.debug { "Repositorio ->\tsaveAll: ${elements.joinToString("\t")}" }

        elements.forEach{ save(it) }
    }

    override fun deleteById(id: Int): Hamburguesa? {
        logger.debug { "Repositorio ->\tdeleteById: $id" }

        val result = almacen.remove(id)

        downgrade()

        return result
    }

    override fun update(element: Hamburguesa): Hamburguesa? {
        logger.debug { "Repositorio ->\tupdate: $element" }

        return updateById(element.id, element)
    }

    override fun updateById(id: Int, element: Hamburguesa): Hamburguesa? {
        logger.debug { "Repositorio ->\tupdateById: $id-$element" }

        if (getById(id) == null) return null
        almacen[id] = element

        downgrade()

        return element
    }

    override fun upgrade(): List<Hamburguesa> {
        logger.info { "Repositorio ->\tupgrade" }

        almacen.clear()

        val load = storageService.loadAll()

        saveAll(load)

        return load
    }

    override fun downgrade(): List<Hamburguesa> {
        logger.info { "Repositorio ->\tdowngrade" }

        return storageService.saveAll(almacen.values.toList())
    }
}