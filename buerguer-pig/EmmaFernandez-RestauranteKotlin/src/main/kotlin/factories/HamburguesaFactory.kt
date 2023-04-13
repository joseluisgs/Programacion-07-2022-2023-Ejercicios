package factories

import java.util.*

object HamburguesaFactory {
    fun crearHamburguesa(nombre: String, ingredientes: List<models.Ingrediente>) =
        models.Hamburguesa(UUID.randomUUID(), nombre, ingredientes)
}