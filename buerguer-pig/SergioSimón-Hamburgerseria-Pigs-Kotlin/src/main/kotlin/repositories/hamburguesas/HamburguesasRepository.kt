package repositories.hamburguesas

import models.Hamburguesa
import repositories.base.ProductosRepository
import java.util.*

interface HamburguesasRepository : ProductosRepository<Hamburguesa, UUID, Unit> {
    fun hamburguesaMostCost(): Hamburguesa?
    fun hamburguesasMostIngrediente(): Hamburguesa?
    fun hamburguesasGroupByNumIngredientes(): Map<Int, List<Hamburguesa>>
    fun midPriceHamburguesas(): Double
}
