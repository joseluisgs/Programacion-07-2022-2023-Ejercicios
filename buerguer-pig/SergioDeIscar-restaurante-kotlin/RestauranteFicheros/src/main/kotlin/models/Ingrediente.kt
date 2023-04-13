package models

import locate.toLocalMoney
import java.io.Serializable

class Ingrediente(val id: Int, val nombre: String, val precio: Float): Serializable {
    fun toDto() = dto.IngredienteDto(id.toString(), nombre, precio.toLocalMoney())

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