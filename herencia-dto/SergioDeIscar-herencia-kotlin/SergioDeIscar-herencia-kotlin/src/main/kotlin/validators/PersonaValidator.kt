package validators

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import errors.PersonaError
import models.Alumno
import models.Persona
import models.Profesor

fun Persona.validate(): Result<Persona, PersonaError> {
    return when {
        id < 0 -> Err(PersonaError.IdError())
        nombre.trim().isBlank() -> Err(PersonaError.NombreError())
        if (this is Alumno) edad < 0 else false -> Err(PersonaError.EdadError())
        if (this is Profesor) modulo.trim().isBlank() else false -> Err(PersonaError.ModuloError())
        else -> Ok(this)
    }
}