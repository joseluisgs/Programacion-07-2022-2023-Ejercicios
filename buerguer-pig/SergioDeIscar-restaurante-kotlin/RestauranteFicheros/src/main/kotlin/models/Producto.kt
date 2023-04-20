package models

import dto.ProductoDto
import java.io.Serializable

abstract class Producto(var id: Long, val nombre: String, val precio: Float): Serializable{
    abstract fun toCsvRow(): String
    //Copy method like data class
    abstract fun copy(id: Long = this.id, nombre: String = this.nombre, precio: Float = this.precio): Producto
}