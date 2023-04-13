package models

import dto.ProductoDto
import locate.toLocalMoney
import java.io.Serializable

class Hamburguesa(
    id: Int,
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

    override fun toDto(): ProductoDto {
        return ProductoDto(
            id.toString(),
            nombre,
            precio.toLocalMoney(),
            "Hamburguesa",
            ingredientes.map { it.toDto() }
        )
    }

    override fun equals(other: Any?): Boolean {
        return other is Hamburguesa && other.nombre == this.nombre && other.ingredientes == this.ingredientes
    }
}