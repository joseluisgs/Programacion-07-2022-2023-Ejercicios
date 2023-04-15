package models

import locale.toLocalMoney
import java.io.Serializable
import java.util.*

data class Hamburguesa(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val receta: List<Ingrediente>,
//    var precio: Double
) : Serializable {
    val precio: Double
        get() = receta.sumOf { it.price }

    override fun toString(): String {
        return "Hamburgesa(name='$name', receta=$receta, precio=${precio.toLocalMoney()}, id=$id)"
    }
}

