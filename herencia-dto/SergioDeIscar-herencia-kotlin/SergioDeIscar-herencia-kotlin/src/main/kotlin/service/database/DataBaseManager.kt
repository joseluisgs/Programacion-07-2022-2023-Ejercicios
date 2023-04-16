package service.database

import config.AppConfig
import mu.KotlinLogging
import java.sql.DriverManager

private val logger = KotlinLogging.logger {  }

object DataBaseManager {
    val dataBase get() = DriverManager.getConnection(AppConfig.APP_DB_URL)
    init {
        logger.debug { "DataBaseManager ->\tinit" }

        if (AppConfig.APP_DB_RESET){
            dropTable()
        }
        createTable()
    }

    private fun createTable() {
        logger.debug { "Creando base de datos si no existe" }

        val sql = """
            CREATE TABLE IF NOT EXISTS tPersona(
                nIdPersona INTEGER PRIMARY KEY AUTOINCREMENT, 
                cNombre TEXT NOT NULL, 
                cTipo TEXT NOT NULL, 
                nEdad INTEGER NULL, 
                cModulo TEXT NULL
            )
        """.trimIndent()
        dataBase.prepareStatement(sql).use { stm ->
            stm.executeUpdate()
        }
    }

    private fun dropTable(){
        logger.debug { "Borrando tabla tPersona" }
        val sql = """DROP TABLE IF EXISTS tPersona"""
        dataBase.prepareStatement(sql).use { stm ->
            stm.executeUpdate()
        }
    }
}