package exceptions

sealed class HamburguesasException(message: String) : Exception(message)
class HambuesaNotFoundException(message: String) : Exception(message)