package errors

sealed class ProductoError(val message: String) {
    class IdError: ProductoError("ERROR: Id no válido")
    class NombreError: ProductoError("ERROR: Nombre no válido")
    class PrecioError: ProductoError("ERROR: Precio no válido")
    class ProductoNoEncontradoError: ProductoError("ERROR: Producto no encontrado")
    class IngredienteError: ProductoError("ERROR: Ingrediente no válido")
    class CapacidadError: ProductoError("ERROR: Capacidad no válida")
}