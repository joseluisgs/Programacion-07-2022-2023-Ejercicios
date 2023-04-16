package mappers

import dto.ProductoDto
import locate.moneyToFloat
import locate.toLocalMoney
import models.Bebida
import models.Hamburguesa
import models.Producto

fun Producto.toDto(): ProductoDto{
    return when(this){
        is Hamburguesa -> ProductoDto(
            id = this.id.toString(),
            nombre = this.nombre,
            precio = this.precio.toLocalMoney(),
            tipo = "Hamburguesa",
            ingredientes = this.ingredientes.map { it.toDto() }
        )
        is Bebida -> ProductoDto(
            id = this.id.toString(),
            nombre = this.nombre,
            precio = this.precio.toLocalMoney(),
            tipo = "Bebida",
            capacidad = this.capacidad.toString()
        )
        else -> throw Exception("Tipo de producto desconocido")
    }
}

fun ProductoDto.toClass(): Producto{
    return when(this.tipo){
        "Hamburguesa" -> Hamburguesa(
            id = this.id.toLong(),
            nombre = this.nombre,
            ingredientes = this.ingredientes!!.map { it.toClass() }
        )
        "Bebida" -> Bebida(
            id = this.id.toLong(),
            nombre = this.nombre,
            precio = this.precio.moneyToFloat(),
            capacidad = this.capacidad!!.toInt()
        )
        else -> throw Exception("Tipo de producto desconocido")
    }
}