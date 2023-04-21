package storage

import factories.ProductoFactory.getProductosDefault
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import services.storage.productos.ProductosStorageService
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class ProductoStorageGenericTest {
    abstract fun filePath(): String
    abstract fun getStorage(): ProductosStorageService

    @AfterAll
    fun cleanFiles(){
        val file = File(filePath())
        if (file.exists()) file.delete()
    }

    @Test
    fun saveAllTest(){
        getStorage().saveAll(getProductosDefault())
        // Comprobar que el fichero existe
        val file = File(filePath())
        assertTrue { file.exists() && file.canWrite() }
    }

    @Test
    fun loadAllTest(){
        val file = File(filePath())
        if (!file.exists()) getStorage().saveAll(getProductosDefault())
        assertAll(
            { assertTrue { file.exists() && file.canRead() } },
            { assertEquals(getProductosDefault().size, getStorage().loadAll().size) }
        )
    }
}