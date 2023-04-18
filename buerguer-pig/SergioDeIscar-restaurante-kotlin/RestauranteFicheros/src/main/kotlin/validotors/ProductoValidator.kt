package validotors

import com.github.michaelbull.result.*
import errors.ProductoError
import models.Bebida
import models.Hamburguesa
import models.Ingrediente
import models.Producto

fun Producto.validate(): Result<Producto, ProductoError> {
    return when {
        id < 0 -> Err(ProductoError.IdError())
        nombre.isBlank() -> Err(ProductoError.NombreError())
        precio < 0 -> Err(ProductoError.PrecioError())
        if (this is Bebida) capacidad < 0 else false -> Err(ProductoError.CapacidadError())
        if (this is Hamburguesa) ingredientes.any { it.validate().getError() != null } else false -> Err(ProductoError.IngredienteError())
        else -> Ok(this)
    }
}

fun Ingrediente.validate(): Result<Ingrediente, ProductoError>{
    return when {
        id < 0 -> Err(ProductoError.IngredienteError())
        nombre.isBlank() -> Err(ProductoError.IngredienteError())
        precio < 0 -> Err(ProductoError.IngredienteError())
        else -> Ok(this)
    }
}