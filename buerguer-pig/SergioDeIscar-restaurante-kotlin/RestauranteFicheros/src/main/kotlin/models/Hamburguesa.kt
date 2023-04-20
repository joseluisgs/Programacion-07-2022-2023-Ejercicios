package models

import dto.ProductoDto
import locate.toLocalMoney
import java.io.Serializable

class Hamburguesa(
    id: Long = 0,
    nombre: String,
    val ingredientes: List<Ingrediente> = emptyList()
): Producto(
        id,
        nombre,
        ingredientes.map { it.precio }.sum()
    ),
    Serializable
{
    override fun toString(): String {
        return "Hamburguesa ($id) -> Nombre: $nombre, Total: ${precio.toLocalMoney()}, Ingredientes: $ingredientes"
    }

    override fun hashCode(): Int {
        return nombre.hashCode() + ingredientes.hashCode()
    }

    override fun toCsvRow(): String {
        return "$id,$nombre,$precio,Hamburguesa,${ingredientes
            .joinToString(separator = "|") { it.toCsvRow() }},\n"
    }

    override fun copy(id: Long, nombre: String, precio: Float): Producto {
        this.id = id
        return Hamburguesa(id, nombre, ingredientes)
    }

    override fun equals(other: Any?): Boolean {
        return other is Hamburguesa && other.nombre == this.nombre && other.ingredientes == this.ingredientes
    }
}