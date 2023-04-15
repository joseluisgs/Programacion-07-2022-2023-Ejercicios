package errors

sealed class InformeError(message: String) {
    class DayError: InformeError("Day no válido")
    class TemMediaError: InformeError("TemMedia no válido")
    class TemMaxPoblacionError: InformeError("TemMaxPoblacion no válido")
    class TemMaxTimeError: InformeError("TemMaxTime no válido")
    class TemMaxError: InformeError("TemMax no válido")
    class TemMinPoblacionError: InformeError("TemMinPoblacion no válido")
    class TemMinTimeError: InformeError("TemMinTime no válido")
    class TemMinError: InformeError("TemMin no válido")
    class PrecipitacionError: InformeError("Precipitacion no válido")
    class InformeNoEncontradoError: InformeError("Informe no encontrado")
}