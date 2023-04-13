package repositories.ingredientes

import models.Ingrediente
import repositories.base.ProductosRepository

interface IngredientesRepository : ProductosRepository<Ingrediente, Int, Unit> {
    fun midPriceIngredientes(): Double
}
