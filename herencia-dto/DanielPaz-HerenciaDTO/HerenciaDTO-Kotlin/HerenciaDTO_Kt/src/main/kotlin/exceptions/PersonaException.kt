package exceptions

sealed class PersonaException(message:String): Exception(message)
class  PersonNotValidException(message: String):PersonaException("ERROR: Persona no valida: $message")
