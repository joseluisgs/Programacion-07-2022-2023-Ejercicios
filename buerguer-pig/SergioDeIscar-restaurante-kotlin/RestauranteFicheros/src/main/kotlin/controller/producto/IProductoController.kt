package controller.producto

import com.github.michaelbull.result.Result
import controller.CrudController
import errors.ProductoError
import models.Bebida
import models.Hamburguesa
import models.Producto

interface IProductoController: CrudController<Producto, Long, ProductoError> {
    fun getOrderByPrecio(): List<Producto>

    fun getProductoMasCaro(): Result<Producto, ProductoError>
    fun getProductoMasBarato(): Result<Producto, ProductoError>
    fun getBebidaConMenosCapacidad(): Result<Bebida, ProductoError>

    // Requisitos
    fun getHamburguesaMasCara(): Result<Hamburguesa, ProductoError>
    fun getHamburguesaConMasIngredientes(): Result<Hamburguesa, ProductoError>
    fun getHamburguesasPrecioMedio(): Double
    fun getPrecioMedioIngredientes(): Map<String,Double>
}