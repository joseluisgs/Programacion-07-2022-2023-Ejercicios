package models

import java.io.Serializable

data class Ingrediente(val id: Int, val nombre: String, val precio: Double) : Serializable {
    override fun toString(): String {
        return "$id,$nombre,$precio"
    }
}