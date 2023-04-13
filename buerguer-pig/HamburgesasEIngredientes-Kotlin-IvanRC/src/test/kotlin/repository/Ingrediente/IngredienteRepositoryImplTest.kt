package repository.Ingrediente

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import model.Ingrediente
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import storageService.Ingrediente.IngredienteStorageService

internal class IngredienteRepositoryImplTest {

    private val ingredientesBase = listOf<Ingrediente>(
        Ingrediente(1, "Cebolla", 25.6),
        Ingrediente(2, "Carne", 10.9),
        Ingrediente(3, "Patata", 13.4),
        Ingrediente(4, "Pan", 3.4),
        Ingrediente(5, "Lechuga", 1.99)
    )

    private var ingredientes = mutableListOf<Ingrediente>()

    @MockK
    private lateinit var storage: IngredienteStorageService

    @InjectMockKs
    private lateinit var repositoryImpl: IngredienteRepositoryImpl

    init{
        MockKAnnotations.init(this)
    }

    @BeforeEach
    fun setUp(){
        ingredientes = ingredientesBase.toMutableList()
    }

    @Test
    fun findById() {
        every { storage.loadAll() } returns ingredientes
        assertEquals(ingredientes[0], repositoryImpl.findById(1))
    }

    @Test
    fun getAll() {
        every { storage.loadAll() } returns ingredientes
        val lista = repositoryImpl.getAll()
        assertAll(
            { assertEquals(5, lista.size) },
            { assertEquals(ingredientes[0], lista[0]) },
            { assertEquals(ingredientes[1], lista[1]) },
            { assertEquals(ingredientes[2], lista[2]) },
            { assertEquals(ingredientes[3], lista[3]) },
            { assertEquals(ingredientes[4], lista[4]) }
        )
    }

    @Test
    fun add() {
        val ingrediente = Ingrediente(6, "Pepinillo", 234.5)
        ingredientes.add(ingrediente)
        every { storage.loadAll() } returns ingredientesBase
        every { storage.saveAll(ingredientes) } returns Unit
        assertEquals(ingrediente, repositoryImpl.add(ingrediente))
    }
}