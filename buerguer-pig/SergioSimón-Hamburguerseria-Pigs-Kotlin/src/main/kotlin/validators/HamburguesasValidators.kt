package validators

import exceptions.IngredienteNoValidoExecption
import exceptions.IngredientesInsufientesExecption
import models.Hamburguesa
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun Hamburguesa.validate() {
    logger.debug { "Validando la siguiente hamburguesa: $this" }
    require(name.isNotBlank()) { throw IngredienteNoValidoExecption("El nombre no puede estar vacío") }
    require(precio >= 0) { throw IngredienteNoValidoExecption("El precio del producto no puede ser menor o igual a 0") }
    require(receta.size >= 7) { throw IngredientesInsufientesExecption("No hay los suficientes ingredientes en la receta por favor introduce al menos 7") }
}