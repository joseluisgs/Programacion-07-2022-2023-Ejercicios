package factories

import models.Hamburguesa
import models.Ingrediente

object HamburguesaFactory {
    fun getRdnHamburguesa(): Hamburguesa{
        return Hamburguesa(
            nombre = getRdnNombre(),
            ingredientes = IngredienteFactory.getRdnIngredientes()
        )
    }

    private fun getRdnNombre(): String{
        return arrayOf("Pequeña", "Mediana", "Grande").random()
    }
}