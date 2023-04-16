package validotors

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import errors.ProductoError
import models.Bebida
import models.Hamburguesa
import models.Producto

fun Producto.validate(): Result<Producto, ProductoError> {
    return when {
        id < 0 -> Err(ProductoError.IdError())
        nombre.isBlank() -> Err(ProductoError.NombreError())
        precio < 0 -> Err(ProductoError.PrecioError())
        if (this is Bebida) capacidad < 0 else false -> Err(ProductoError.CapacidadError())
        if (this is Hamburguesa) ingredientes.isEmpty() else false -> Err(ProductoError.IngredienteError())
        else -> Ok(this)
    }
}