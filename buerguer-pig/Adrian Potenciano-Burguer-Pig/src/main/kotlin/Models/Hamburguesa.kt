package Models

import java.util.*

data class Hamburguesa(
    val id: UUID,
    val nombre: String,
    val ingredientes: List<Ingrediente>,
    val precio: Double
){

}