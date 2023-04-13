package service.storage.persona

import config.AppConfig
import dto.PersonaDto
import models.Persona
import mu.KotlinLogging
import java.sql.DriverManager

private val logger = KotlinLogging.logger {  }

object PersonaDB: PersonaStorageService{
    override fun saveAll(elements: List<Persona>): List<Persona> {
        logger.debug { "PersonaDB ->\tsaveAll" }
        elements.map { it.toDto() }.forEach{
            val connexion = DriverManager.getConnection(AppConfig.APP_DB_URL)
            val sentencia = connexion.prepareStatement(
                """INSERT INTO tPersona(nIdPersona, cNombre, cTipo, nEdad, cModulo) VALUES (?, ?, ?, ?, ?)"""
            )
            sentencia.setInt(1, it.id.toInt())
            sentencia.setString(2, it.nombre)
            sentencia.setString(3, it.tipo)

            if (it.edad != null) sentencia.setInt(4, it.edad.toInt())
            else sentencia.setNull(4, java.sql.Types.INTEGER)

            sentencia.setString(5, it.modulo)
            sentencia.executeUpdate()
            connexion.close()
        }
        return elements
    }

    override fun loadAll(): List<Persona> {
        logger.debug { "PersonaDB ->\tloadAll" }
        val connexion = DriverManager.getConnection(AppConfig.APP_DB_URL)
        val sentencia = connexion.prepareStatement("""SELECT * FROM tPersona""")
        val result = sentencia.executeQuery()
        val personas = mutableListOf<Persona>()

        while (result.next()){
            val id = result.getInt("nIdPersona").toString()
            val nombre = result.getString("cNombre")
            val tipo = result.getString("cTipo")
            val edad = result.getInt("nEdad").toString()
            val modulo = result.getString("cModulo")
            personas.add(
                PersonaDto(
                    id,nombre,tipo,edad,modulo
                ).toClass()
            )
        }

        connexion.close()
        return personas.toList()
    }
}