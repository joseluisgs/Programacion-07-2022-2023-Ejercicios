package exception.ingredienteException

sealed class IngredienteException(mes: String): RuntimeException(mes)
class IngredienteNotFoundException(mes: String): IngredienteException("Error al buscar ingrediente, el ingrediente de id: $mes, no se ha encontreado.")
class IngredienteBadRequestException(mes: String): IngredienteException("Error al introducir datos del ingrediente, el dato $mes, no es valido.")
class IngredienteAlreadyExistsException(mes: String): IngredienteException("Error al añadir ingrediente, el ingrediente de id: $mes, ya existe.")
