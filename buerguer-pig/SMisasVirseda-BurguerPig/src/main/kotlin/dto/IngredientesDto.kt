package dto

import com.squareup.moshi.Json
import models.Ingrediente
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


@Root(name = "ingredientes")
data class IngredientesDto(
    @field:ElementList(name = "ingredientes", inline = true)
    @param:ElementList(name = "ingredientes", inline = true)
    @Json(name = "ingredientes")
    val ingredientes: List<IngredienteDto>
)

fun List<Ingrediente>.toDto() = IngredientesDto(ingredientes = this.map { it.toDto() })
fun IngredientesDto.toIngredientes() = ingredientes.map { it.toIngrediente() }
