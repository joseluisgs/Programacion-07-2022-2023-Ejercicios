package factories

import models.Bebida
import models.Hamburguesa
import models.Ingrediente
import models.Producto

object ProductoFactory {
    fun getRdnProductos(
        probability: Array<Int> = arrayOf(50,50),
        size: Int = 25
    ): List<Producto>{
        require(probability.size == 2) { throw IllegalArgumentException("Probability tiene que tener 2 elementos") }
        require(probability.sum() == 100) { throw IllegalArgumentException("La suma de los elementos de probability tiene que ser 100") }
        require(probability.any { it > 0 }) { throw IllegalArgumentException("Los elementos de probability tienen que ser mayores que 0") }
        return (1..size).map { getRdnProducto(probability) } // Sin tener que hacer una lista mutable
    }

    private fun getRdnProducto(
        probability: Array<Int> = arrayOf(50,50)
    ): Producto{
        return if (probability[0] > (0..100).random()){
            HamburguesaFactory.getRdnHamburguesa()
        } else {
            BebidaFactory.getRdnBebida()
        }
    }

    fun getProductosDefault() = listOf(
        Hamburguesa(1, "Grande", listOf(
            Ingrediente(1, "tomate", 2.2f),
            Ingrediente(2, "lechuga", 3.2f)
        )),
        Bebida(2, "Fanta", 2.0f, 250),
        Hamburguesa(3, "Pequeña", listOf(
            Ingrediente(3, "queso", 1.2f)
        )),
        Bebida(4, "Coca Cola", 2.0f, 250)
    )
}