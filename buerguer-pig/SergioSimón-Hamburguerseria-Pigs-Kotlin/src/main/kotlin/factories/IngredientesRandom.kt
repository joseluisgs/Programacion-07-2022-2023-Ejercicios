package factories

import models.Ingrediente
import mu.KotlinLogging
import kotlin.random.Random

private val logger = KotlinLogging.logger {}

private var contador = 0
private var id = 0

fun createIngredienteRandom(): Ingrediente {
    val ingredientes = arrayOf("Pepinillo", "Pan", "Lechuga", "Queso", "Carne", "Cebolla", "Ketchup")
    val ingredienteAuto = ingredienteBucle(ingredientes)
    val randomPrecio = (Random.nextDouble(0.5, 5.0) * 100).toInt()
    val precio = (randomPrecio.toDouble()) / 100
    id++

    logger.debug { "Creando ingrediente con id: $id nombre: $ingredienteAuto, y precio $precio" }
    return Ingrediente(id, ingredienteAuto, precio)
}

private fun ingredienteBucle(ingredientes: Array<String>): String {
    return if (contador < 6) {
        contador++
        ingredientes[contador]
    } else {
        contador = 0
        ingredientes[contador]
    }
}

fun restablecerFactory() {
    logger.debug { "Reseteando el contador de id a 0 " }
    id = 0
}
