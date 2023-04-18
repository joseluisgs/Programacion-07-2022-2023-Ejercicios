package repositories

import config.AppConfig
import factories.PersonaFactory.getPersonasDefault
import models.Alumno
import models.Profesor
import org.apache.ibatis.jdbc.ScriptRunner
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import repositories.persona.PersonaRepositoryDataBase
import service.database.DataBaseManager
import java.io.BufferedReader
import java.io.FileReader
import java.io.Reader

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonaRepositoryDataBaseTest {
    private val repository = PersonaRepositoryDataBase

    @BeforeEach
    fun tearDown() {
        // Eliminar tablas
        executeSQLFile(AppConfig.APP_DB_RESET_PATH)
        // Crear tablas
        executeSQLFile(AppConfig.APP_DB_INIT_PATH)
        // Insertar datos
        repository.saveAll(getPersonasDefault())
    }

    private fun executeSQLFile(sqlFile: String ){
        val sr = ScriptRunner(DataBaseManager.dataBase)
        val reader: Reader = BufferedReader(FileReader(sqlFile))
        sr.runScript(reader)
    }

    @Test
    fun findAllTest(){
        val personas = repository.findAll()
        assertAll(
            { assertEquals(getPersonasDefault().size, repository.findAll().toList().size) },
            { assert(personas.toList()[0] is Alumno)},
            { assert(personas.toList()[1] is Profesor)},
            { assert(personas.toList()[2] is Alumno)},
            { assert(personas.toList()[3] is Profesor)},
        )
    }

    @Test
    fun findByIdTest(){
        val result = repository.findById(1)
        val resultFail = repository.findById(5)
        assertAll(
            { assertEquals(getPersonasDefault()[0], result) },
            { assertEquals(null, resultFail) },
        )
    }

    @Test
    fun createTest(){
        val alumno = getPersonasDefault()[2].copy(id = -1, nombre = "NEW_Alumno")
        val profesor = getPersonasDefault()[3].copy(id = -1, nombre = "NEW_Profesor")
        repository.save(alumno)
        repository.save(profesor)
        assertAll(
            { assertEquals(5, alumno.id) },
            { assertEquals(6, profesor.id) },
            { assertEquals(alumno, repository.findById(5)) },
            { assertEquals(profesor, repository.findById(6)) },
            { assertEquals(6, repository.findAll().toList().size) }
        )
    }

    @Test
    fun updateTest(){
        val alumno = getPersonasDefault()[2].copy(nombre = "NEW_Alumno")
        val profesor = getPersonasDefault()[3].copy(nombre = "NEW_Profesor")
        repository.save(alumno)
        repository.save(profesor)
        assertAll(
            { assertEquals(3, alumno.id) },
            { assertEquals(4, profesor.id) },
            { assertEquals(alumno, repository.findById(3)) },
            { assertEquals(profesor, repository.findById(4)) },
            { assertEquals(4, repository.findAll().toList().size) }
        )
    }

    @Test
    fun deleteById(){
        repository.deleteById(1)
        assertAll(
            { assertEquals(3, repository.findAll().toList().size) },
            { assertEquals(null, repository.findById(1)) },
        )
    }

    @Test
    fun deleteByElement(){
        repository.delete(getPersonasDefault()[0])
        assertAll(
            { assertEquals(3, repository.findAll().toList().size) },
            { assertEquals(null, repository.findById(1)) },
        )
    }

    @Test
    fun existsByIdTest(){
        assertAll(
            { assertEquals(true, repository.existsById(1)) },
            { assertEquals(false, repository.existsById(5)) },
        )
    }
}