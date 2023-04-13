package models

import dto.PedidoDto
import java.io.Serializable
import java.time.LocalDateTime

data class Pedido(
    val userId: Int,
    val productos: List<LineaPedido>,
    val createAt: LocalDateTime = LocalDateTime.now(),
    val total: Float = productos.map { it.precioUnd * it.cantidad }.sum()
): Serializable{
    fun toDto() = PedidoDto(userId.toString(), productos.map { it.toDto() }, createAt.toString(), total.toString())
}