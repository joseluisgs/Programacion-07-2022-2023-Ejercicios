package exceptions

sealed class HamborguesaException(message: String?) : Exception(message)

class JsonException(message: String?) : HamborguesaException(message)
