package models

import dto.ProductoDto
import java.io.Serializable

abstract class Producto(val id: Int, val nombre: String, val precio: Float): Serializable {
    fun toLineaPedido(cantidad: Int = 1): LineaPedido {
        return LineaPedido(this.id, precio, cantidad)
    }
    abstract fun toDto(): ProductoDto
}