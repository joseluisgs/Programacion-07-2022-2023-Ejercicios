package factory

import model.Hamburgesa
import model.Ingrediente
import mu.KotlinLogging
import java.util.*

object HamburgesaFactory {

    private val logger = KotlinLogging.logger {}

    fun createRandom(ingredientes: List<Ingrediente>): Hamburgesa{
        val nombres = arrayOf("Hambuergesa de vaca", "Hambuergesa de pollo", "Hambuergesa de cerdo")
        val ingredientesHamburger = mutableListOf<Ingrediente>()
        val numero = (1..8).random()
        for(i in 1..numero){
            ingredientesHamburger.add(ingredientes.random())
        }
        return Hamburgesa(UUID.randomUUID(), nombres.random(), ingredientesHamburger)
    }
}