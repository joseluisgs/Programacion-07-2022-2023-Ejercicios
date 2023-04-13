package models

import dto.LineaPedidoDto
import java.io.Serializable

data class LineaPedido(val productoId: Int, val precioUnd: Float, val cantidad: Int): Serializable{
    fun toDto() = LineaPedidoDto(productoId.toString(), precioUnd.toString(), cantidad.toString())
}