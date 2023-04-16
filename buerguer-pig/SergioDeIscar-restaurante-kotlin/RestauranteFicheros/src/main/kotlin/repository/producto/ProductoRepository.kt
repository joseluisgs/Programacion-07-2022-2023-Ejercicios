package repository.producto

import models.Bebida
import models.Hamburguesa
import models.Producto
import repository.CrudRepository

interface ProductoRepository: CrudRepository<Producto, Long>{
    fun getOrderByPrecio(): List<Producto>

    fun getProductoMasCaro(): Producto?
    fun getProductoMasBarato(): Producto?
    fun getBebidaConMenosCapacidad(): Bebida?

    // Requisitos
    fun getHamburguesaMasCara(): Hamburguesa?
    fun getHamburguesaConMasIngredientes(): Hamburguesa?
    fun getHamburguesasPrecioMedio(): Double
    fun getPrecioMedioIngredientes(): Map<String,Double>
}