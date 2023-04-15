package factories

import models.Hamburguesa
import models.Ingrediente

object HamburguesaFactory {
    private var nextId = 0

    fun getRdnHamburguesa(): Hamburguesa{
        return Hamburguesa(
            id = nextId++,
            nombre = getRdnNombre(arrayOf("Pequeña", "Mediana", "Grande")),
            ingredientes = IngredienteFactory.getRdnIngredientes()
        )
    }
}