package validators

import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import errors.PersonaError
import models.Alumno
import models.Profesor
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PersonaValidatorTest {
    @Test
    fun validatePersonaOk() {
        val alumno = Alumno(1, "Pepe", 20)
        val resultAlumno = alumno.validate()
        val profesor = Profesor(2, "Juan", "Kotlin")
        val resultProfesor = profesor.validate()

        assertAll(
            { assert(resultAlumno.getError() == null) },
            { assert(resultAlumno.get() == alumno) },
            { assert(resultProfesor.getError() == null) },
            { assert(resultProfesor.get() == profesor) },
        )
    }

    @Test
    fun validateAlumnoIdFail() {
        val alumno = Alumno(-1, "Pepe", 20)
        val resultAlumno = alumno.validate()
        assertAll(
            { assert(resultAlumno.getError() != null) },
            { assert(resultAlumno.getError() is PersonaError.IdError) }
        )
    }

    @Test
    fun validateAlumnoNombreFail() {
        val alumno = Alumno(1, " ", 20)
        val resultAlumno = alumno.validate()
        assertAll(
            { assert(resultAlumno.getError() != null) },
            { assert(resultAlumno.getError() is PersonaError.NombreError) }
        )
    }

    @Test
    fun validateAlumnoEdadFail() {
        val alumno = Alumno(1, "Pepe", -1)
        val resultAlumno = alumno.validate()
        assertAll(
            { assert(resultAlumno.getError() != null) },
            { assert(resultAlumno.getError() is PersonaError.EdadError) }
        )
    }

    @Test
    fun validateProfesorIdFail() {
        val profesor = Profesor(-1, "Juan", "Kotlin")
        val resultProfesor = profesor.validate()
        assertAll(
            { assert(resultProfesor.getError() != null) },
            { assert(resultProfesor.getError() is PersonaError.IdError) }
        )
    }

    @Test
    fun validateProfesorNombreFail() {
        val profesor = Profesor(1, "", "Kotlin")
        val resultProfesor = profesor.validate()
        assertAll(
            { assert(resultProfesor.getError() != null) },
            { assert(resultProfesor.getError() is PersonaError.NombreError) }
        )
    }

    @Test
    fun validateProfesorModuloFail() {
        val profesor = Profesor(1, "Juan", "")
        val resultProfesor = profesor.validate()
        assertAll(
            { assert(resultProfesor.getError() != null) },
            { assert(resultProfesor.getError() is PersonaError.ModuloError) }
        )
    }
}