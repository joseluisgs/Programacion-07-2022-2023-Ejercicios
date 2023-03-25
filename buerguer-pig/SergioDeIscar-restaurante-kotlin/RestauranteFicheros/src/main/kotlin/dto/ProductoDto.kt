package dto

data class ProductoDto(
    val id: String,
    val nombre: String,
    val precio: String,
    val tipo: String,
    val ingredientes: List<IngredienteDto>? = null,
    val capacidad: String? = null
)