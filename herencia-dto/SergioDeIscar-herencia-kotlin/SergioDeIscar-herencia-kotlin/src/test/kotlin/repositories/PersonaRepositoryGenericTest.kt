package repositories

import factories.PersonaFactory.getPersonasDefault
import models.Alumno
import models.Profesor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import repositories.persona.PersonaRepository

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class PersonaRepositoryGenericTest {
    private lateinit var repo: PersonaRepository

    abstract fun getRepository(): PersonaRepository

    @BeforeEach
    open fun setUp() {
        repo = getRepository()
        repo.deleteAll()
        repo.saveAll(getPersonasDefault())
    }

    @Test
    open fun findAllTest() {
        val personas = repo.findAll()
        assertAll(
            { assertEquals(getPersonasDefault().size, repo.findAll().toList().size) },
            { assert(personas.toList()[0] is Alumno) },
            { assert(personas.toList()[1] is Profesor) },
            { assert(personas.toList()[2] is Alumno) },
            { assert(personas.toList()[3] is Profesor) },
        )
    }

    @Test
    open fun findByIdTest(){
        val result = repo.findById(1)
        val resultFail = repo.findById(5)
        assertAll(
            { assertEquals(getPersonasDefault()[0], result) },
            { assertEquals(null, resultFail) },
        )
    }

    @Test
    open fun createTest(){
        val alumno = getPersonasDefault()[2].copy(id = -1, nombre = "NEW_Alumno")
        val profesor = getPersonasDefault()[3].copy(id = -1, nombre = "NEW_Profesor")
        repo.save(alumno)
        repo.save(profesor)
        assertAll(
            { assertEquals(5, alumno.id) },
            { assertEquals(6, profesor.id) },
            { assertEquals(alumno, repo.findById(5)) },
            { assertEquals(profesor, repo.findById(6)) },
            { assertEquals(6, repo.findAll().toList().size) }
        )
    }

    @Test
    open fun updateTest(){
        val alumno = getPersonasDefault()[2].copy(nombre = "NEW_Alumno")
        val profesor = getPersonasDefault()[3].copy(nombre = "NEW_Profesor")
        repo.save(alumno)
        repo.save(profesor)
        assertAll(
            { assertEquals(3, alumno.id) },
            { assertEquals(4, profesor.id) },
            { assertEquals(alumno, repo.findById(3)) },
            { assertEquals(profesor, repo.findById(4)) },
            { assertEquals(4, repo.findAll().toList().size) }
        )
    }

    @Test
    open fun deleteByIdTest(){
        repo.deleteById(1)
        assertAll(
            { assertEquals(3, repo.findAll().toList().size) },
            { assertEquals(null, repo.findById(1)) },
        )
    }

    @Test
    open fun deleteByElementTest(){
        repo.delete(getPersonasDefault()[0])
        assertAll(
            { assertEquals(3, repo.findAll().toList().size) },
            { assertEquals(null, repo.findById(1)) },
        )
    }

    @Test
    open fun deleteAllTest(){
        repo.deleteAll()
        assertAll(
            { assertEquals(0, repo.findAll().toList().size) },
        )
    }

    @Test
    open fun existsByIdTest(){
        assertAll(
            { assertEquals(true, repo.existsById(1)) },
            { assertEquals(false, repo.existsById(5)) },
        )
    }
}