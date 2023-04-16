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
        val personas = mutableListOf<Persona>()

        val sql = """SELECT * FROM tPersona"""
        DataBaseManager.dataBase.prepareStatement(sql).use { stm ->
            val result = stm.executeQuery()
            while (result.next()){
                // Al hacerlo con Dto el mapper me sirve
                personas.add(
                    PersonaDto(
                        result.getLong("nIdPersona").toString(),
                        result.getString("cNombre"),
                        result.getString("cTipo"),
                        result.getInt("nEdad").toString(),
                        result.getString("cModulo")
                    ).toClass()
                )
            }
        }
        return personas.toList()
    }

    override fun findById(id: Long): Persona? {
        logger.debug { "PersonaRepositoryMap ->\tgetById" }
        var persona: Persona? = null

        val sql = """SELECT * FROM tPersona WHERE nIdPersona = ?"""
        DataBaseManager.dataBase.prepareStatement(sql).use { stm ->
            stm.setLong(1, id)
            val result = stm.executeQuery()
            if (result.next()){
                persona = PersonaDto(
                    result.getLong("nIdPersona").toString(),
                    result.getString("cNombre"),
                    result.getString("cTipo"),
                    result.getInt("nEdad").toString(),
                    result.getString("cModulo")
                ).toClass()
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
        val sql = """INSERT INTO tPersona(cNombre, cTipo, nEdad, cModulo) VALUES (?, ?, ?, ?)"""
        DataBaseManager.dataBase.use {
            it.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS).use { stm ->
                setParametersStm(stm, element.toDto())

                stm.executeUpdate()

                val claves = stm.generatedKeys
                if (claves.next()) newID = claves.getLong(1)
            }
        }
        return element.copy(id = newID)
    }
    private fun update(element: Persona): Persona {
        logger.info { "PersonaRepositoryMap ->\tupdate" }
        val sql = """UPDATE tPersona SET cNombre = ?, cTipo = ?, nEdad = ?, cModulo = ? WHERE nIdPersona = ?"""
        DataBaseManager.dataBase.prepareStatement(sql).use { stm ->
            setParametersStm(stm, element.toDto())
            stm.executeUpdate()
        }
        return element
    }

    private fun setParametersStm(stm: PreparedStatement, dto: PersonaDto){
        stm.setString(1, dto.nombre)
        stm.setString(2, dto.tipo)
        if (dto.edad != null) stm.setInt(3, dto.edad.toInt())
        else stm.setNull(3, java.sql.Types.INTEGER)
        stm.setString(4, dto.modulo)
    }

    override fun saveAll(elements: Iterable<Persona>, storage: Boolean) {
        logger.debug { "PersonaRepositoryMap ->\tsaveAll" }
        elements.forEach{ save(it, storage) }
    }

    override fun deleteById(id: Long): Boolean{
        logger.debug { "PersonaRepositoryMap ->\tdeleteById" }
        var result: Int
        val sql = """DELETE FROM tPersona WHERE nIdPersona = ?"""
        DataBaseManager.dataBase.prepareStatement(sql).use { stm ->
            stm.setLong(1, id)
            result = stm.executeUpdate()
        }
        return result == 1
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