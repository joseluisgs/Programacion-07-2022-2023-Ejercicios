package factory

import model.Ingrediente
import mu.KotlinLogging

class IngredienteFactory {

    private val logger = KotlinLogging.logger {}

    companion object {
        var contador = 1
        fun getNextId(): Int{
            return contador++
        }

        private var instance: IngredienteFactory? = null

        fun getInstance(): IngredienteFactory{
            if(instance == null){
                instance = IngredienteFactory()
            }
            return instance!!
        }
    }

    fun createSomeRandom(): List<Ingrediente> {
        logger.debug { "Se inicia el factory que creará algún ingrediente aleatorio" }
        val ingredientes = mutableListOf<Ingrediente>()
        val nombres = arrayOf("Salsa", "Carne", "Lechuga", "Cebolla", "Pepinillo", "Pan")
        val numero = (1..6).random()
        for(i in 1..numero){
            val precio: Double = ((0..750).random() / 100.0)
            var ingrediente: Ingrediente = Ingrediente(getNextId(), nombres.random(), precio)
            ingredientes.add(ingrediente)
        }
        return ingredientes.toList()
    }

}
