package models

import java.io.Serializable

class Ingrediente(var ID: Int = nextId(), var nombre: String, var precio: Double) : Serializable{
    constructor(): this(0,"",0.0)


    // id autonumérico
    companion object{
        var id = 1
        fun nextId(): Int{
            return id++
        }

        fun parseFromString(inputString: String): Ingrediente {
            val atributes = inputString.split("/")
            val id = atributes[0].toInt()
            val nombre = atributes[1].trim()
            val precio = atributes[2].toDouble()
            return Ingrediente(id, nombre, precio)
        }
    }
    override fun toString(): String {
        return "$ID/$nombre/$precio"
    }


}