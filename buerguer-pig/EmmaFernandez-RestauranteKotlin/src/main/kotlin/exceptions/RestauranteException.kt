package exceptions

sealed class RestauranteException(message: String) : Exception(message)
class IngredienteNoEncontradoException(message: String) : RestauranteException(message)
class HamburguesaNoEncontradaException(message: String) : RestauranteException(message)
class IngredienteYaExisteException(message: String) : RestauranteException(message)
class HamburguesaYaExisteException(message: String) : RestauranteException(message)