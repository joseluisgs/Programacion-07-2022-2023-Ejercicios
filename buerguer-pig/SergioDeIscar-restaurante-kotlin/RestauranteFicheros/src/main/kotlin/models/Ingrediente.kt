package models

import locate.toLocalMoney
import java.io.Serializable

class Ingrediente(var id: Long = 0, val nombre: String, val precio: Float): Serializable {

    //Copy method like data class
    fun copy(id: Long = this.id, nombre: String = this.nombre, precio: Float = this.precio): Ingrediente {
        this.id = id
        return Ingrediente(id, nombre, precio)
    }
    override fun toString(): String {
        return "Ingrediente ($id) -> Nombre: $nombre ; Precio: ${precio.toLocalMoney()}"
    }

    override fun hashCode(): Int {
        return nombre.hashCode() + precio.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return other is Ingrediente && other.nombre == this.nombre && other.precio == this.precio
    }
}