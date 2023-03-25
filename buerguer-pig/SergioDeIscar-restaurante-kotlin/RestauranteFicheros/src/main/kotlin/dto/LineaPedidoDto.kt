package dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "linea_pedido")
data class LineaPedidoDto(
    @field:Attribute(name = "id_producto")
    @param:Attribute(name = "id_producto")
    val productoId: String,

    @field:Attribute(name = "precio_und")
    @param:Attribute(name = "precio_und")
    val precioUnd: String,

    @field:Attribute(name = "cantidad")
    @param:Attribute(name = "cantidad")
    val cantidad: String
)