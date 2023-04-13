package dto

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "pedidos")
data class PedidosDto(
    @field:ElementList(name = "pedido", inline = true)
    @param:ElementList(name = "pedido", inline = true)
    val pedidos: List<PedidoDto>
)