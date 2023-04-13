package factories

import models.Ingrediente
import kotlin.random.Random

object IngredienteFactory {
    fun getRdnIngredientes(): List<Ingrediente>{
        val ingredientes = mutableListOf<Ingrediente>()
        repeat((1..5).random()){
            ingredientes.add(getRdnIngrediente())
        }
        return ingredientes.toList()
    }

    fun getRdnIngrediente(): Ingrediente{
        return Ingrediente(
            nombre = getRdnNombre(),
            precio = getRdnPrecio()
        )
    }

    private fun getRdnNombre(): String{
        return arrayOf("Tomate", "Lechuga", "Cebolla").random()
    }

    private fun getRdnPrecio(): Float{
        return (0..10).random() + Random.nextFloat()
    }
}