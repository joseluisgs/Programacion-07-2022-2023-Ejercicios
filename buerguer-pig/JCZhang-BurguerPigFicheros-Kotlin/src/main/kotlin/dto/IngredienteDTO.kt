package dto


import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "Ingrediente")
data class IngredienteDTO(

    @field:Attribute(name = "id")
    @param:Attribute(name = "id")
    var id: Int,

    @field:Element(name = "nombre")
    @param:Element(name = "nombre")
    val nombre: String,

    @field:Attribute(name = "precio")
    @param:Attribute(name = "precio")
    val precio: Double
)


@Root(name="ingredientes")
data class IngredientesListDto(
    @field: ElementList(name = "ingredientes", inline= true)
    @param: ElementList(name = "ingredientes", inline= true)
    val ingredientes: List<IngredienteDTO>
)