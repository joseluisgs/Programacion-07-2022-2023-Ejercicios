package models

import java.io.Serializable
import java.util.*

data class Hamburguesa(val id: UUID, val nombre: String, val ingredientes: List<Ingrediente>) : Serializable {
    var precio = 0.0

    init {
        ingredientes.forEach { precio += it.precio }
    }

    override fun toString(): String {
        return "$id,$nombre,${ingredientes.joinToString(":")},$precio"
    }
}
