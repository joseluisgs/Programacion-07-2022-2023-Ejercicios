package repositories

import config.AppConfig
import factories.PersonaFactory.getPersonasDefault
import models.Alumno
import models.Profesor
import org.apache.ibatis.jdbc.ScriptRunner
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import repositories.persona.PersonaRepository
import repositories.persona.PersonaRepositoryDataBase
import service.database.DataBaseManager
import java.io.BufferedReader
import java.io.FileReader
import java.io.Reader

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonaRepositoryDataBaseTest: PersonaRepositoryGenericTest() {
    private val repository = PersonaRepositoryDataBase

    @BeforeEach
    fun tearDown() {
        // Eliminar tablas
        executeSQLFile(AppConfig.APP_DB_RESET_PATH)
        // Crear tablas
        executeSQLFile(AppConfig.APP_DB_INIT_PATH)
        // Limpiar tablas (no es necesario)
        repository.deleteAll()
        // Insertar datos
        repository.saveAll(getPersonasDefault())
    }

    private fun executeSQLFile(sqlFile: String ){
        val sr = ScriptRunner(DataBaseManager.dataBase)
        val reader: Reader = BufferedReader(FileReader(sqlFile))
        sr.runScript(reader)
    }

    override fun getRepository() = repository
}