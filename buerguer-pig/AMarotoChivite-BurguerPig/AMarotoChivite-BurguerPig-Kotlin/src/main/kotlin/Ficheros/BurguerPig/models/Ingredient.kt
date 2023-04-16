package Ficheros.BurguerPig.models

import java.io.Serializable

class Ingredient(val name: String, val price: Double) : Serializable {
    private var id: Int = autoCount()

    constructor(name: String, price: Double, id: Int) : this(
        name,
        price
    ) {
        this.id = id
    }

    fun getID(): Int {
        return id
    }

    override fun toString(): String {
        return "Ingredient(name='$name', price=$price, id=$id)"
    }

    companion object {
        var counter = 0
        private fun autoCount(): Int {
            return counter++
        }
    }
}