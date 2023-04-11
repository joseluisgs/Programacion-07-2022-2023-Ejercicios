package dto.hamburgesa

import dto.ingrediente.IngredienteDto
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "hamburgesa")
data class HamburgesaDto (
    @field:Attribute(name = "id")
    @param:Attribute(name = "id")
    val id: String,

    @field:Attribute(name = "nombre")
    @param:Attribute(name = "nombre")
    val nombre: String,

    @field:ElementList(name = "ingredientes", inline = true)
    @param:ElementList(name = "ingredientes", inline = true)
    val ingredientes: List<IngredienteDto>
    )