package repository.productos

import models.Producto
import repository.CrudRepository

interface ProductoRepository: CrudRepository<Producto, Int>{
    fun getAllOrderByPrecio(): List<Producto>
}