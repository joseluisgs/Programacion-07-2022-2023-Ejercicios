package errors

sealed class DuplaError(message: String) {
    class PoblacionError: DuplaError("Población no válida")
    class ProvinciaError: DuplaError("Provincia no válida")
    class TemMaxError: DuplaError("Temperatura máxima no válida")
    class TimeMaxError: DuplaError("Hora máxima no válida")
    class TemMinError: DuplaError("Temperatura mínima no válida")
    class TimeMinError: DuplaError("Hora mínima no válida")
    class PrecipitacionError: DuplaError("Precipitación no válida")
    class DayError: DuplaError("Fecha no válida")
    class DuplaNoEncontradaError: DuplaError("Dupla no encontrada")
}