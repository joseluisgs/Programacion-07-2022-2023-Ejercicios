package factories

import models.Bebida
import models.Hamburguesa

object BebidaFactory {
    fun getRdnBebida(): Bebida {
        return Bebida(
            nombre = getRdnNombre(arrayOf("Fanta","Agua","CocaCola")),
            precio = getRdnPrecio(3..12),
            capacidad = getRdnCapacidad()
        )
    }

    private fun getRdnCapacidad(): Int{
        return (150..1200).random()
    }
}