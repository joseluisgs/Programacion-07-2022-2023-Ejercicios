package models

import locate.toLocalMoney
import java.io.Serializable

class Hamburguesa(val id: Int = count++, val nombre: String, val ingredientes: List<Ingrediente>): Serializable {
    companion object{
        var count = 0
    }

    val precioTotal = ingredientes.map { it.precio }.sum()

    override fun toString(): String {
        return "Hamburguesa ($id) -> Nombre: $nombre, Total: ${precioTotal.toLocalMoney()}, Ingredientes: $ingredientes"
    }

    override fun hashCode(): Int {
        return nombre.hashCode() + ingredientes.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return other is Hamburguesa && other.nombre == this.nombre && other.ingredientes == this.ingredientes
    }
}