package storage

import factories.PersonaFactory.getPersonasDefault
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import service.storage.persona.PersonaStorageService
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class PersonaStorageGenericTest {
    abstract fun filePath(): String
    abstract fun getStorage(): PersonaStorageService

    @AfterAll
    fun cleanFiles(){
        val file = File(filePath())
        if (file.exists()) file.delete()
    }

    @Test
    fun saveAllTest(){
        getStorage().saveAll(getPersonasDefault())
        // Comprobar que el fichero existe
        val file = File(filePath())
        assertTrue { file.exists() && file.canWrite() }
    }

    @Test
    fun loadAllTest(){
        val file = File(filePath())
        if (!file.exists()) getStorage().saveAll(getPersonasDefault())
        assertAll(
            { assertTrue { file.exists() && file.canRead() } },
            { assertEquals(getPersonasDefault().size, getStorage().loadAll().size) }
        )
    }
}