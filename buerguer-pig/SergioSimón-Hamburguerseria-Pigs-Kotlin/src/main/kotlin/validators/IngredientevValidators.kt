package validators

import exceptions.IngredienteNoValidoExecption
import models.Ingrediente
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun Ingrediente.validate() {
    logger.debug { "Validando el siguiente ingrediente: $this" }
    require(name.isNotBlank()) { throw IngredienteNoValidoExecption("El nombre no puede estar vacío") }
    require(price >= 0) { throw IngredienteNoValidoExecption("El precio del producto no puede ser menor o igual a 0") }
}