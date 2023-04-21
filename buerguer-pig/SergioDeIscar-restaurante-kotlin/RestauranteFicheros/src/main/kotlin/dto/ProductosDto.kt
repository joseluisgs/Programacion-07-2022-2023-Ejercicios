package dto

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "productos")
data class ProductosDto(
    @field:ElementList(name = "producto", inline = true)
    @param:ElementList(name = "producto", inline = true)
    val productos: List<ProductoDto>
)