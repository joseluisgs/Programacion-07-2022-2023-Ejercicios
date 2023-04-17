package service.database

import config.AppConfig
import mu.KotlinLogging
import org.apache.ibatis.jdbc.ScriptRunner
import java.io.BufferedReader
import java.io.FileReader
import java.io.Reader
import java.sql.DriverManager

private val logger = KotlinLogging.logger {  }

object DataBaseManager {
    val dataBase get() = DriverManager.getConnection(AppConfig.APP_DB_URL)
    init {
        logger.debug { "DataBaseManager ->\tinit" }

        if (AppConfig.APP_DB_RESET){
            logger.debug { "DataBaseManager ->\tinit ->\treset" }
            executeSQLFile(AppConfig.APP_DB_RESET_PATH)
        }
        executeSQLFile(AppConfig.APP_DB_INIT_PATH)
    }

    private fun executeSQLFile(sqlFile: String ){
        val sr = ScriptRunner(dataBase)
        val reader: Reader = BufferedReader(FileReader(sqlFile))
        sr.runScript(reader)
    }
}