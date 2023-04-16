package services.database

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

        val tProducto = """CREATE TABLE IF NOT EXISTS tProducto (
            nIdProducto INTEGER PRIMARY KEY AUTOINCREMENT,
            cNombre TEXT NOT NULL,
            nPrecio REAL NOT NULL
        )""".trimIndent()

        val tBebida = """CREATE TABLE IF NOT EXISTS tBebida (
            nIdBebida INTEGER PRIMARY KEY,
            nCapacidad INTEGER NOT NULL,
            FOREIGN KEY (nIdBebida) REFERENCES tProducto(nIdProducto)
        )""".trimIndent()

        val tIngrediente = """CREATE TABLE IF NOT EXISTS tIngrediente (
            nIdIngrediente INTEGER PRIMARY KEY AUTOINCREMENT,
            cNombre TEXT NOT NULL,
            nPrecio REAL NOT NULL
        )""".trimIndent()

        val tHamburguesa = """CREATE TABLE IF NOT EXISTS tHamburguesa (
            nIdHamburguesa INTEGER PRIMARY KEY,
            FOREIGN KEY (nIdHamburguesa) REFERENCES tProducto(nIdProducto)
        )""".trimIndent()

        val tHamburguesa_Ingrediente = """CREATE TABLE IF NOT EXISTS tHamburguesa_Ingrediente (
            id_hamburguesa INTEGER NOT NULL,
            id_ingrediente INTEGER NOT NULL,
            PRIMARY KEY (id_hamburguesa, id_ingrediente),
            FOREIGN KEY (id_hamburguesa) REFERENCES tHamburguesa(nIdHamburguesa),
            FOREIGN KEY (id_ingrediente) REFERENCES tIngrediente(nIdIngrediente)
        )""".trimIndent()

        val connection = dataBase
        connection.autoCommit = false

        connection.prepareStatement(tProducto).use {
            it.executeUpdate()
        }

        connection.prepareStatement(tBebida).use {
            it.executeUpdate()
        }

        connection.prepareStatement(tIngrediente).use {
            it.executeUpdate()
        }

        connection.prepareStatement(tHamburguesa).use {
            it.executeUpdate()
        }

        connection.prepareStatement(tHamburguesa_Ingrediente).use {
            it.executeUpdate()
        }

        connection.commit()
        println()
    }

    private fun dropTable(){
        logger.debug { "Borrando tabla tPersona" }
        val tProducto = """DROP TABLE IF EXISTS tProducto"""
        val tBebida = """DROP TABLE IF EXISTS tBebida"""
        val tIngrediente = """DROP TABLE IF EXISTS tIngrediente"""
        val tHamburguesa = """DROP TABLE IF EXISTS tHamburguesa"""
        val tHamburguesa_Ingrediente = """DROP TABLE IF EXISTS tHamburguesa_Ingrediente"""
        val connection = dataBase
        connection.autoCommit = false

        connection.prepareStatement(tHamburguesa_Ingrediente).use {
            it.executeUpdate()
        }

        connection.prepareStatement(tHamburguesa).use {
            it.executeUpdate()
        }

        connection.prepareStatement(tIngrediente).use {
            it.executeUpdate()
        }

        connection.prepareStatement(tBebida).use {
            it.executeUpdate()
        }

        connection.prepareStatement(tProducto).use {
            it.executeUpdate()
        }

        connection.commit()
    }
}