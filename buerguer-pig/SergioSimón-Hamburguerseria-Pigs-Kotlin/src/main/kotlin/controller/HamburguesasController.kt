package controller

import exceptions.HambuesaNotFoundException
import exceptions.IngredienteNoEncontrado
import factories.restablecerFactory
import models.Hamburguesa
import mu.KotlinLogging
import repositories.hamburguesas.HamburguesasRepository
import validators.validate
import java.util.*

private val logger = KotlinLogging.logger {}

class HamburguesasController(
    private val hamburguesasRepository: HamburguesasRepository
) {
    fun findAll(): List<Hamburguesa> {
        logger.debug { "Buscando todos los ingredientes" }
        return hamburguesasRepository.findAll()
    }

    fun save(entity: Hamburguesa): Hamburguesa {
        logger.debug { "Guardando Ingrediente en el repository" }
        entity.validate()
        return hamburguesasRepository.save(entity)
    }

    fun findById(id: UUID): Hamburguesa {
        logger.debug { "Buscando por Id un Ingrediente en el repository" }
        return hamburguesasRepository.findById(id)
            ?: throw IngredienteNoEncontrado("Ingrediente con la siguiente $id no ha sido encontrado")
    }

    fun clear() {
        logger.debug { "Borrando almacén desde el controller de hamburguesas y reseteando el contador del factory" }
        restablecerFactory()
        return hamburguesasRepository.clear()
    }

    // Hamburguesa más cara
    fun hamburguesaMasCara(): Hamburguesa {
        return hamburguesasRepository.hamburguesaMostCost()
            ?: throw HambuesaNotFoundException("Hamburguesa no encontrada al buscar la con mayor precio")
    }

    // Hamburguesa con más ingredientes
    fun hamburguesaMasIngredientes(): Hamburguesa {
        return hamburguesasRepository.hamburguesasMostIngrediente()
            ?: throw HambuesaNotFoundException("Hamburguesa no encontrada al buscar la con mayor cantidad de ingredientes")
    }

    // Número de Hamburguesas por ingrediente
    fun hamburguesasAgrupadoPorNumIngredientes(): Map<Int, List<Hamburguesa>> {
        return hamburguesasRepository.hamburguesasGroupByNumIngredientes()
    }

    // Precio medio de las hamburguesas
    fun precioMedioHamburguesa(): Double {
        return hamburguesasRepository.midPriceHamburguesas()
    }
}