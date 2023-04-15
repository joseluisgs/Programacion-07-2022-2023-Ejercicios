package models

import dto.ProductoDto
import locate.toLocalMoney
import java.io.Serializable

class Bebida(id: Int, nombre: String, precio: Float, val capacidad: Int): Producto(id, nombre, precio), Serializable {
    override fun toString(): String {
        return "Bebida ($id) -> Nombre: $nombre ; Precio: ${precio.toLocalMoney()} ; Capacidad: $capacidad"
    }

    override fun hashCode(): Int {
        return nombre.hashCode() + precio.hashCode() + capacidad.hashCode()
    }

    override fun toDto(): ProductoDto {
        return ProductoDto(
            id.toString(),
            nombre,
            precio.toLocalMoney(),
            "Bebida",
            null,
            capacidad.toString()
        )
    }

    override fun equals(other: Any?): Boolean {
        return other is Bebida && other.nombre == this.nombre && other.precio == this.precio && other.capacidad == this.capacidad
    }
}