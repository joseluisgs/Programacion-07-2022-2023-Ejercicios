package factories

import models.Ingrediente
import kotlin.random.Random

object IngredienteFactory {
    private var nextId = 0

    fun getRdnIngredientes(): List<Ingrediente>{
        val ingredientes = mutableListOf<Ingrediente>()
        repeat((1..5).random()){
            ingredientes.add(getRdnIngrediente())
        }
        return ingredientes.toList()
    }

    fun getRdnIngrediente(): Ingrediente{
        return Ingrediente(
            id = nextId++,
            nombre = getRdnNombre(arrayOf("Tomate", "Lechuga", "Cebolla")),
            precio = getRdnPrecio(1..10)
        )
    }
}