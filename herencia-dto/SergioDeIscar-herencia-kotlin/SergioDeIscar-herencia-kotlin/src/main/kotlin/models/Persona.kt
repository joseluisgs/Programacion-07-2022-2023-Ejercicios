package models

import config.AppConfig
import dto.PersonaDto
import java.sql.DriverManager
import java.sql.SQLException

abstract class Persona(
    val id: Int = count++,
    val nombre: String
){
    abstract fun toDto(): PersonaDto
    abstract fun toCsvRow(): String
    companion object{
        var count = getLastIdFromDB()

        private fun getLastIdFromDB(): Int {
            val connexion = DriverManager.getConnection(AppConfig.APP_DB_URL)
            val selectMaxId = connexion.prepareStatement("""SELECT MAX(nIdPersona) FROM tPersona""")
            val result = selectMaxId.executeQuery()
            connexion.close()
            return if (result.next()) {
                try {
                    result.getInt(1)
                } catch (e: SQLException) {
                    0
                }
            } else 0
        }
    }
    fun nextId(): Int{
        return count++
    }
}
