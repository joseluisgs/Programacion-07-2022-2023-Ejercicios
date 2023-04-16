package models

import dto.ProductoDto
import locate.toLocalMoney
import java.io.Serializable

class Bebida(id: Long = 0, nombre: String, precio: Float, val capacidad: Int): Producto(id, nombre, precio), Serializable {
    override fun toString(): String {
        return "Bebida ($id) -> Nombre: $nombre ; Precio: ${precio.toLocalMoney()} ; Capacidad: $capacidad"
    }

    override fun hashCode(): Int {
        return nombre.hashCode() + precio.hashCode() + capacidad.hashCode()
    }

    override fun copy(id: Long, nombre: String, precio: Float): Producto {
        this.id = id
        return Bebida(id, nombre, precio, capacidad)
    }

    override fun equals(other: Any?): Boolean {
        return other is Bebida && other.nombre == this.nombre && other.precio == this.precio && other.capacidad == this.capacidad
    }
}