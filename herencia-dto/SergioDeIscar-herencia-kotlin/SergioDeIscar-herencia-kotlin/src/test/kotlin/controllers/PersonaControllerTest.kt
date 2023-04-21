package controllers

import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import controllers.persona.PersonaController
import errors.PersonaError
import factories.PersonaFactory.getPersonasDefault
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import models.Alumno
import models.Profesor
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith
import repositories.persona.PersonaRepositoryMap
import service.storage.persona.PersonaFileCsv
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class PersonaControllerTest {
    @MockK
    private lateinit var storage: PersonaFileCsv
    @MockK
    private lateinit var repo: PersonaRepositoryMap

    @InjectMockKs
    private lateinit var controller: PersonaController

    private val personasErrors = listOf(
        Alumno(-1, "Pepe", 20),               // Id error
        Profesor(2, "", "Programación"),    // Nombre error
        Alumno(3, "Ana", -4),                 // Edad error
        Profesor(4, "Juan", "")             // Materia error
    )

    @Test
    fun findAllTest(){
        every { repo.findAll() } returns getPersonasDefault()
        assertEquals(
            getPersonasDefault().size,
            controller.findAll().toList().size
        )
        verify { repo.findAll() }
    }

    @Test
    fun findByIdTest(){
        every { repo.findById(0) } returns getPersonasDefault()[0]
        every { repo.findById(5) } returns null
        assertAll(
            { assertEquals(getPersonasDefault()[0], controller.findById(0).get()) },
            { assertTrue { controller.findById(5).getError() is PersonaError.PersonaNoEncontradaError } }
        )
        verify { repo.findById(0) }
        verify { repo.findById(5) }
    }

    @Test
    fun saveTest(){
        every { repo.save(getPersonasDefault()[0]) } returns getPersonasDefault()[0]
        assertAll(
            { assertEquals(getPersonasDefault()[0], controller.save(getPersonasDefault()[0]).get()) },
            { assertTrue { controller.save(personasErrors[0]).getError() is PersonaError.IdError } },
            { assertTrue { controller.save(personasErrors[1]).getError() is PersonaError.NombreError } },
            { assertTrue { controller.save(personasErrors[2]).getError() is PersonaError.EdadError } },
            { assertTrue { controller.save(personasErrors[3]).getError() is PersonaError.ModuloError } }
        )
        verify { repo.save(getPersonasDefault()[0]) }
    }

    @Test
    fun saveAllTest(){
        every { repo.saveAll(getPersonasDefault()) } returns Unit
        assertAll(
            { assertTrue { controller.saveAll(getPersonasDefault()).get() != null } },
            { assertTrue { controller.saveAll(personasErrors).getError() is PersonaError.IdError } },
            { assertTrue { controller.saveAll(personasErrors.subList(1,4)).getError() is PersonaError.NombreError } },
            { assertTrue { controller.saveAll(personasErrors.subList(2,4)).getError() is PersonaError.EdadError } },
            { assertTrue { controller.saveAll(personasErrors.subList(3,4)).getError() is PersonaError.ModuloError } }
        )
        verify { repo.saveAll(getPersonasDefault()) }
    }

    @Test
    fun deleteByIdTest(){
        every { repo.deleteById(0) } returns true
        every { repo.deleteById(5) } returns false
        assertAll(
            { assertTrue { controller.deleteById(0).get() != null } },
            { assertTrue { controller.deleteById(5).getError() is PersonaError.PersonaNoEncontradaError } }
        )
        verify { repo.deleteById(0) }
        verify { repo.deleteById(5) }
    }

    @Test
    fun deleteTest(){
        every { repo.delete(getPersonasDefault()[0]) } returns true
        every { repo.delete(personasErrors[0]) } returns false
        assertAll(
            { assertTrue { controller.delete(getPersonasDefault()[0]).get() != null } },
            { assertTrue { controller.delete(personasErrors[0]).getError() is PersonaError.PersonaNoEncontradaError } }
        )
        verify { repo.delete(getPersonasDefault()[0]) }
        verify { repo.delete(personasErrors[0]) }
    }

    @Test
    fun existsByIdTest(){
        every { repo.existsById(0) } returns true
        every { repo.existsById(5) } returns false
        assertAll(
            { assertTrue { controller.existsById(0).get() != null } },
            { assertTrue { controller.existsById(5).getError() is PersonaError.PersonaNoEncontradaError } }
        )
        verify { repo.existsById(0) }
        verify { repo.existsById(5) }
    }
}