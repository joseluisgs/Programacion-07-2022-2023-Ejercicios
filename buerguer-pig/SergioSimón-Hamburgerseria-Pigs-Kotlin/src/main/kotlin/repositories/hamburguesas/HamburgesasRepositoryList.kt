package repositories.hamburguesas

import models.Hamburguesa
import mu.KotlinLogging
import service.storage.hamburguesas.HamburguesasStorageService
import java.util.*

private val logger = KotlinLogging.logger {}


class hamburguesasRepositoryList(
    private val storageService: HamburguesasStorageService
) : HamburguesasRepository {

    private val almacen = mutableListOf<Hamburguesa>()

    // Hamburguesa más cara
    override fun hamburguesaMostCost(): Hamburguesa? {
        return almacen.maxByOrNull { it.precio }
    }

    // Hamburguesa con más ingredientes
    override fun hamburguesasMostIngrediente(): Hamburguesa? {
        return almacen.maxByOrNull { it.receta.size }
    }

    // Número de Hamburguesas por ingrediente
    override fun hamburguesasGroupByNumIngredientes(): Map<Int, List<Hamburguesa>> {
        return almacen.groupBy { it.receta.size }
    }

    // Precio medio de las hamburguesas
    override fun midPriceHamburguesas(): Double {
        var res = almacen.sumOf { it.precio } / almacen.count()
        res = redondear(res)
        return res
    }

    // Redondea el precio a dos decimales (truncamiento)
    private fun redondear(price: Double): Double {
        return ((price * 100).toInt()).toDouble() / 100
    }

    override fun findAll(): List<Hamburguesa> {
        logger.debug { "findAll hamburguesas" }
        loadAll()
        return almacen
    }

    override fun save(entity: Hamburguesa): Hamburguesa {
        almacen.add(entity)
        saveAll()
        return entity
    }

    override fun findById(id: UUID): Hamburguesa? {
        return almacen.find { it.id == id }
    }

    override fun clear() {
        logger.debug { "Borrando almacén desde el repository" }
        return almacen.clear()
    }

    private fun saveAll() {
        logger.debug { "Guardando hamburguesas al almacenamiento" }
        storageService.saveAll(almacen)
    }

    private fun loadAll() {
        logger.debug { "Cargando hamburguesas del almacenamiento" }
        almacen.clear()
        storageService.loadAll().forEach {
            almacen.add(it)
        }
    }
}