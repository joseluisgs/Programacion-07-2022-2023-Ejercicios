package Ficheros.BurguerPig.models

import java.io.Serializable
import java.util.*

class Burguer(val name: String, val ingredients: List<Ingredient>) : Serializable {
    private var id: UUID
    fun getUUID(): UUID {
        return id
    }

    private var priceCalculate: Double
    fun getPrice(): Double {
        return priceCalculate
    }

    constructor(name: String, ingredients: List<Ingredient>, id: UUID, priceCalculate: Double) : this(
        name,
        ingredients
    ) {
        this.id = id
        this.priceCalculate = priceCalculate
    }

    init {
        id = UUID.randomUUID()
        priceCalculate = ingredients.sumOf { it.price } * 1.5
    }

    override fun toString(): String {
        return "Burguer(name='$name', ingredients=$ingredients, id=$id, priceCalculate=$priceCalculate)"
    }

}