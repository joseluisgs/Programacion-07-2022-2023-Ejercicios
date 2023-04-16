package factories

import models.Hamburguesa
import models.Ingrediente

object HamburguesaFactory {
    fun getRdnHamburguesa(): Hamburguesa{
        return Hamburguesa(
            nombre = getRdnNombre(arrayOf("Pequeña", "Mediana", "Grande")),
            ingredientes = IngredienteFactory.getRdnIngredientes()
        )
    }
}