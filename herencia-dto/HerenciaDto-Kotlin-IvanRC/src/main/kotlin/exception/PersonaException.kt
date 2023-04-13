package exception

sealed class PersonaException(message: String): RuntimeException(message)
class PersonaBadRequestException(message: String): PersonaException(message)
