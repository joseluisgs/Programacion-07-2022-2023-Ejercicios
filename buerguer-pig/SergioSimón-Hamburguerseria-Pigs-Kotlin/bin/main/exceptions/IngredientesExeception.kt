package exceptions

sealed class IngredientesExecption(message: String) : Exception(message)
class IngredienteNoEncontrado(message: String) : IngredientesExecption(message)
class IngredienteNoValidoExecption(message: String) : IngredientesExecption(message)
class IngredientesInsufientesExecption(message: String) : IngredientesExecption(message)