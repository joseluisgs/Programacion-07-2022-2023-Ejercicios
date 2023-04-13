package exception.hamburgesaException

sealed class HamburgesaException(mes: String): RuntimeException(mes)
class HamburgesaNotFoundException(mes: String): HamburgesaException("Error al buscar hamburgesa, la hamburgesa de id: $mes, no se ha encontrado.")
class HamburgesaBadRequestException(mes: String): HamburgesaException("Error al introducir datos de la hamburgesa, el dato $mes, no es valido.")
class HamburgesaAlreadyExistsException(mes: String): HamburgesaException("Error al añadir hamburgesa, la hamburgesa de id: $mes, ya existe.")
