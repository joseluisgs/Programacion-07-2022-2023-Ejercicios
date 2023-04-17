package repositories.ingredientes

import models.Ingrediente
import mu.KotlinLogging
import service.storage.ingredientes.IngredientesStorageService

class IngredientesRepositoryList(
    private val storageService: IngredientesStorageService
) : IngredientesRepository {
    private val almacen: MutableList<Ingrediente> = mutableListOf()

    private val logger = KotlinLogging.logger {}

    // Precio medio de los ingredientes
    override fun midPriceIngredientes(): Double {
        var res = almacen.sumOf { it.price } / almacen.count()
        res = redondear(res)
        return res
    }

    // Redondea el precio a dos decimales (truncamiento)
    private fun redondear(price: Double): Double {
        return ((price * 100).toInt()).toDouble() / 100
    }

    override fun findAll(): List<Ingrediente> {
        logger.debug { "findAll ingredientes" }
        loadAll()
        return almacen
    }

    override fun save(entity: Ingrediente): Ingrediente {
        logger.debug { "Guardando ingrediente: $entity" }
        almacen.add(entity)

        logger.debug { "Guardando un nuevo ingrediente: $entity" }
        saveAll()

        return entity
    }

    override fun findById(id: Int): Ingrediente? {
        logger.debug { "Buscando producto con id $id" }
        loadAll()
        return almacen.find { it.id == id }
    }

    override fun clear() {
        return almacen.clear()
    }

    private fun saveAll() {
        logger.debug { "Guardando ingredientes al almacenamiento" }
        storageService.saveAll(almacen)
    }

    private fun loadAll() {
        logger.debug { "Cargando ingredientes del almacenamiento" }
        almacen.clear()
        storageService.loadAll().forEach {
            almacen.add(it)
        }
    }
}