package repositories.persona

import config.AppConfig
import dto.PersonaDto
import mappers.toClass
import mappers.toDto
import models.Alumno
import models.Persona
import models.Profesor
import mu.KotlinLogging
import service.database.DataBaseManager
import java.sql.PreparedStatement
import java.sql.Statement

private val logger = KotlinLogging.logger {  }

class PersonaRepositoryDataBase(): PersonaExtension {
    override fun getPorcentajePorTipo(): Map<String, Double> {
        logger.debug { "PersonaRepositoryMap ->\tgetPorcentajePorTipo" }
        val personas = findAll()
        return personas.groupBy {
            when(it){
                is Alumno -> "Alumno"
                is Profesor -> "Profesor"
                else -> throw RuntimeException("Tipo de persona no reconocido")
            }
        }.mapValues { it.value.size.toDouble() / personas.count()}
    }

    override fun findAll(): Iterable<Persona> {
        logger.debug { "PersonaRepositoryMap ->\tgetAll" }

        return findAlumnos() + findProfesores()
    }

    private fun findProfesores(): List<Profesor>{
        val profesores = mutableListOf<Profesor>()
        val sql = """
            SELECT nIdPersona, cNombre, tP.cModulo FROM tPersona
            INNER JOIN tProfesor tP on tPersona.nIdPersona = tP.nIdProfesor;
            """.trimIndent()
        DataBaseManager.dataBase.prepareStatement(sql).use { stm ->
            val result = stm.executeQuery()
            while (result.next()){
                profesores.add(
                    Profesor(
                        result.getLong("nIdPersona"),
                        result.getString("cNombre"),
                        result.getString("cModulo")
                    )
                )
            }
        }
        return profesores.toList()
    }

    private fun findAlumnos(): List<Alumno>{
        val alumnos = mutableListOf<Alumno>()
        val sql = """
            SELECT nIdPersona, cNombre, tA.nEdad FROM tPersona
            INNER JOIN tAlumno tA on tPersona.nIdPersona = tA.nIdAlumno;
            """.trimIndent()
        DataBaseManager.dataBase.prepareStatement(sql).use { stm ->
            val result = stm.executeQuery()
            while (result.next()){
                alumnos.add(
                    Alumno(
                        result.getLong("nIdPersona"),
                        result.getString("cNombre"),
                        result.getInt("nEdad")
                    )
                )
            }
        }
        return alumnos.toList()
    }

    override fun findById(id: Long): Persona? {
        logger.debug { "PersonaRepositoryMap ->\tgetById" }
        return findByIdAlumno(id) ?: findByIdProfesor(id)
    }

    private fun findByIdProfesor(id: Long): Persona? {
        var persona: Persona? = null
        val sql = """
            SELECT nIdPersona, cNombre, tP.cModulo FROM tPersona
            INNER JOIN tProfesor tP on tPersona.nIdPersona = tP.nIdProfesor
            WHERE nIdPersona = ?
        """.trimIndent()
        DataBaseManager.dataBase.prepareStatement(sql).use { stm ->
            stm.setLong(1, id)
            val result = stm.executeQuery()
            if (result.next()){
                persona = Profesor(
                    result.getLong("nIdPersona"),
                    result.getString("cNombre"),
                    result.getString("cModulo")
                )
            }
        }

        return persona
    }

    private fun findByIdAlumno(id: Long): Persona? {
        var persona: Persona? = null
        val sql = """
            SELECT nIdPersona, cNombre, tA.nEdad FROM tPersona
            INNER JOIN tAlumno tA on tPersona.nIdPersona = tA.nIdAlumno
            WHERE nIdPersona = ?
            """.trimIndent()

        DataBaseManager.dataBase.prepareStatement(sql).use { stm ->
            stm.setLong(1, id)
            val result = stm.executeQuery()
            if (result.next()){
                persona = Alumno(
                    result.getLong("nIdPersona"),
                    result.getString("cNombre"),
                    result.getInt("nEdad")
                )
            }
        }

        return persona
    }

    override fun save(element: Persona, storage: Boolean): Persona {
        logger.debug { "PersonaRepositoryMap ->\tsave" }
        return if (existsById(element.id)){
            update(element)
        }else{
            create(element)
        }
    }

    private fun create(element: Persona): Persona {
        logger.info { "PersonaRepositoryMap ->\tcreate" }
        var newID: Long = -1
        val sql = """INSERT INTO tPersona(cNombre) VALUES (?)"""
        DataBaseManager.dataBase.use {
            it.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS).use { stm ->
                stm.setString(1, element.nombre)
                stm.executeUpdate()
                val result = stm.generatedKeys
                if (result.next()){
                    newID = result.getLong(1)
                }
            }
        }
        element.copy(id = newID)
        when(element){
            is Alumno -> createAlumno(element)
            is Profesor -> createProfesor(element)
        }
        return element
    }

    private fun createProfesor(element: Profesor) {
        val sql = """INSERT INTO tProfesor(nIdProfesor, cModulo) VALUES (?, ?)"""
        DataBaseManager.dataBase.use {
            it.prepareStatement(sql).use { stm ->
                stm.setLong(1, element.id)
                stm.setString(2, element.modulo)
                stm.executeUpdate()
            }
        }
    }

    private fun createAlumno(element: Alumno) {
        val sql = """INSERT INTO tAlumno(nIdAlumno, nEdad) VALUES (?, ?)"""
        DataBaseManager.dataBase.use {
            it.prepareStatement(sql).use { stm ->
                stm.setLong(1, element.id)
                stm.setInt(2, element.edad)
                stm.executeUpdate()
            }
        }
    }

    private fun update(element: Persona): Persona {
        logger.info { "PersonaRepositoryMap ->\tupdate" }
        val sql = """UPDATE tPersona SET cNombre = ? WHERE nIdPersona = ?"""
        DataBaseManager.dataBase.prepareStatement(sql).use { stm ->
            stm.setString(1, element.nombre)
            stm.setLong(2, element.id)
            stm.executeUpdate()
        }
        when(element){
            is Alumno -> updateAlumno(element)
            is Profesor -> updateProfesor(element)
        }
        return element
    }

    private fun updateProfesor(element: Profesor) {
        val sql = """UPDATE tProfesor SET cModulo = ? WHERE nIdProfesor = ?"""

        DataBaseManager.dataBase.use {
            it.prepareStatement(sql).use { stm ->
                stm.setString(1, element.modulo)
                stm.setLong(2, element.id)
                stm.executeUpdate()
            }
        }
    }

    private fun updateAlumno(element: Alumno) {
        val sql = """UPDATE tAlumno SET nEdad = ? WHERE nIdAlumno = ?"""

        DataBaseManager.dataBase.use {
            it.prepareStatement(sql).use { stm ->
                stm.setInt(1, element.edad)
                stm.setLong(2, element.id)
                stm.executeUpdate()
            }
        }
    }

    override fun saveAll(elements: Iterable<Persona>, storage: Boolean) {
        logger.debug { "PersonaRepositoryMap ->\tsaveAll" }
        elements.forEach{ save(it, storage) }
    }

    override fun deleteById(id: Long): Boolean{
        logger.debug { "PersonaRepositoryMap ->\tdeleteById" }
        var result: Int
        val persona = findById(id) ?: return false

        result = when(persona){
            is Alumno -> deleteAlumno(persona)
            is Profesor -> deleteProfesor(persona)
            else -> 0
        }

        val sql = """DELETE FROM tPersona WHERE nIdPersona = ?"""
        DataBaseManager.dataBase.prepareStatement(sql).use { stm ->
            stm.setLong(1, id)
            result = stm.executeUpdate()
        }

        return result == 1
    }

    private fun deleteProfesor(persona: Profesor): Int {
        val result: Int
        val sql = """DELETE FROM tProfesor WHERE nIdProfesor = ?"""
        DataBaseManager.dataBase.prepareStatement(sql).use { stm ->
            stm.setLong(1, persona.id)
            result = stm.executeUpdate()
        }
        return result
    }

    private fun deleteAlumno(persona: Alumno): Int {
        val result: Int
        val sql = """DELETE FROM tAlumno WHERE nIdAlumno = ?"""
        DataBaseManager.dataBase.prepareStatement(sql).use { stm ->
            stm.setLong(1, persona.id)
            result = stm.executeUpdate()
        }
        return result
    }

    override fun delete(element: Persona): Boolean {
        logger.debug { "PersonaRepositoryMap ->\tdelete" }
        return deleteById(element.id)
    }

    override fun existsById(id: Long): Boolean {
        logger.debug { "PersonaRepositoryMap ->\texistsById" }
        return findById(id) != null
    }
}