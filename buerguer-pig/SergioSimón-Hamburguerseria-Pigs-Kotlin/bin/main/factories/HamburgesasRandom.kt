package factories

import models.Hamburguesa
import models.Ingrediente
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}


fun createHamburguesaRandom(receta: MutableList<Ingrediente>): Hamburguesa {
    val nombre = "Hamburger"
//    val randomPrecio = (Random.nextDouble(8.0, 20.0) * 100).toInt()
//    val precio = (randomPrecio.toDouble()) / 100
//    val precio = receta.sumOf { it.price }

//    logger.debug { "Creando $nombre con un precio de ${precio.toLocalMoney()} y con la receta: $receta" }
//    logger.debug { "Creando $nombre con y con la receta: $receta" }
    return Hamburguesa(name = nombre, receta = receta)
}