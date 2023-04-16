package Ficheros.BurguerPig.models.dto

import com.squareup.moshi.JsonAdapter
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "hamburguesa")
data class BurguerDTO(
    @field:Attribute(name = "name")
    @param:Attribute(name = "name")
    val name: String,
    // El inline es para que no se vea la jerarquía de la lista, y sea más legible
    @field:ElementList(name = "ingredientes", inline = true)
    @param:ElementList(name = "ingredientes", inline = true)
    val ingredients: List<IngredientDTO>,
    @field:Attribute(name = "uuid")
    @param:Attribute(name = "uuid")
    val uuid: String,
    @field:Attribute(name = "precio")
    @param:Attribute(name = "precio")
    val price: String
)

@Root(name = "hamburguesas")
data class BurguerListDto(
    // El inline es para que no se vea la jerarquía de la lista, y sea más legible
    @field:ElementList(name = "hamburguesa", inline = true)
    @param:ElementList(name = "hamburguesa", inline = true)
    val burguers: List<BurguerDTO>
)