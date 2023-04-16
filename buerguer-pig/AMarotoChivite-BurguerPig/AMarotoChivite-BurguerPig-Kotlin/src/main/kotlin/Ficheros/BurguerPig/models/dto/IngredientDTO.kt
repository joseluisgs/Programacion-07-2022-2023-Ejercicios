package Ficheros.BurguerPig.models.dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

// Para XML
@Root(name = "ingrediente")
data class IngredientDTO(
    // Para XML
    @field:Attribute(name = "name")
    @param:Attribute(name = "name")
    val name: String,
    @field:Attribute(name = "precio")
    @param:Attribute(name = "precio")
    val price: String,
    @field:Attribute(name = "id")
    @param:Attribute(name = "id")
    val id: String
)

@Root(name = "ingredientes")
data class IngredientListDto(
    // El inline es para que no se vea la jerarquía de la lista, y sea más legible
    @field:ElementList(name = "ingrediente", inline = true)
    @param:ElementList(name = "ingrediente", inline = true)
    val ingredients: List<IngredientDTO>
)