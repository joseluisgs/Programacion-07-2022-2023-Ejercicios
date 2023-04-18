package repositories

import config.AppConfig
import factories.ProductoFactory.getProductosDefault
import models.Bebida
import models.Hamburguesa
import org.apache.ibatis.jdbc.ScriptRunner
import org.junit.jupiter.api.*
import repository.producto.ProductoRepository
import repository.producto.ProductoRepositoryDataBase
import services.database.DataBaseManager
import java.io.BufferedReader
import java.io.FileReader
import java.io.Reader
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductoRepositoryDataBaseTest: ProductoRepositoryGenericTest() {
    private var repository = ProductoRepositoryDataBase
    override fun getRepository() = repository

    @BeforeEach
    fun tearDown(){
        // Eliminar tablas
        executeSQLFile(AppConfig.APP_DB_RESET_PATH)
        // Crear tablas
        executeSQLFile(AppConfig.APP_DB_INIT_PATH)
        // Limpiar tablas (no es necesario)
        repository.deleteAll()
        // Insertar datos
        repository.saveAll(getProductosDefault())
    }

    private fun executeSQLFile(sqlFile: String ){
        val sr = ScriptRunner(DataBaseManager.dataBase)
        val reader: Reader = BufferedReader(FileReader(sqlFile))
        sr.runScript(reader)
    }
}