package models

import java.io.Serializable
import java.util.UUID

data class Hamburguesa(val id: UUID, val nombre: String, val ingredientes: List<Ingrediente>) : Serializable {
    val precio: Double
        get() = ingredientes.sumOf { it.precio }

    override fun toString(): String {
        return "$id,$nombre,${ingredientes.joinToString(";").replace(",", ":")},$precio"
    }
}