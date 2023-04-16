package factories

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
}