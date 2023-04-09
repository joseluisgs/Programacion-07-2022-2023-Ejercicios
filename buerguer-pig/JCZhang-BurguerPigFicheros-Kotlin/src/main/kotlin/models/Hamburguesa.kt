package models

import java.io.Serializable
import java.util.UUID


data class Hamburguesa(
    var id: UUID = UUID.randomUUID(),
    var nombre: String,
    val ingredientes: List<Ingrediente>,
    var precio: Double = calcularPrecio(ingredientes)
): Serializable {


    companion object {
        private fun calcularPrecio(ingredientes: List<Ingrediente>): Double {
            return ingredientes.sumOf { it.precio }
        }
    }


    override fun toString(): String {
        return "Hamburguesa(id=$id, nombre='$nombre', ingredientes=$ingredientes, precio=$precio)"
    }

}