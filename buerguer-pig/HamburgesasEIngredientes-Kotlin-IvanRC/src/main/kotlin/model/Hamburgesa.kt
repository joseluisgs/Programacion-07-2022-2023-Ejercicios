package model

import java.io.Serializable
import java.util.UUID

data class Hamburgesa(val id: UUID, val nombre: String, val ingredientes: MutableList<Ingrediente>): Serializable{
    val precio: Double
        get() = ingredientes.sumOf { it.precio }

    override fun toString(): String {
        return "Hamburgesa(id=$id, nombre='$nombre', ingredientes=$ingredientes, precio=$precio)"
    }
}