package dto.ingrediente

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root
data class ListaIngredientesDto (
    @field:ElementList(name = "ingredientes", inline = true)
    @param:ElementList(name = "ingredientes", inline = true)
    val ingredientesDto: List<IngredienteDto>
)
