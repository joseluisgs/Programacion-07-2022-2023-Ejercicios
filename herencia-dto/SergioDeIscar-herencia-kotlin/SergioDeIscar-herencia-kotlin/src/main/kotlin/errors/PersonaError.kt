package errors

sealed class PersonaError(val message: String) {
    class IdError: PersonaError("ERROR: Id no válido")
    class NombreError: PersonaError("ERROR: Nombre no válido")
    class PersonaNoEncontradaError: PersonaError("ERROR: Persona no encontrada")
    class EdadError: PersonaError("ERROR: Edad no válida")
    class ModuloError: PersonaError("ERROR: Módulo no válido")
}