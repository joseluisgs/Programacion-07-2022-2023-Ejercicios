package dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "pedido")
data class PedidoDto(
    @field:Attribute(name = "id_usuario")
    @param:Attribute(name = "id_usuario")
    val userId: String,

    @field:ElementList(name = "productos", inline = true)
    @param:ElementList(name = "productos", inline = true)
    val productos: List<LineaPedidoDto>,

    @field:Attribute(name = "create_at")
    @param:Attribute(name = "create_at")
    val createAt: String,

    @field:Attribute(name = "total")
    @param:Attribute(name = "total")
    val total: String
)
