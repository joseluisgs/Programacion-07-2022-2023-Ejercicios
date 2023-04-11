package controller

import exception.ingredienteException.IngredienteAlreadyExistsException
import exception.ingredienteException.IngredienteNotFoundException
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import model.Ingrediente
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import repository.Ingrediente.IngredienteRepositoryImpl
import storageService.Ingrediente.IngredienteStorageService

internal class IngredienteControllerTest {

    private val ingredientesBase = listOf<Ingrediente>(
        Ingrediente(1, "Cebolla", 25.6),
        Ingrediente(2, "Carne", 10.9),
        Ingrediente(3, "Patata", 13.4),
        Ingrediente(4, "Pan", 3.4),
        Ingrediente(5, "Lechuga", 1.99)
    )

    private var ingredientes = mutableListOf<Ingrediente>()

    @MockK
    private lateinit var repositoryImpl: IngredienteRepositoryImpl

    @InjectMockKs
    private lateinit var controller: IngredienteController

    init{
        MockKAnnotations.init(this)
    }

    @BeforeEach
    fun setUp(){
        ingredientes = ingredientesBase.toMutableList()
    }

    @Test
    fun findById() {
        every { repositoryImpl.findById(1) } returns ingredientesBase[0]
        assertEquals(ingredientesBase[0], controller.findById("1"))
    }

    @Test
    fun findByIdBuNotFound() {
        every { repositoryImpl.findById(1) } returns null
        val mes = assertThrows<IngredienteNotFoundException> { controller.findById("1") }
        assertEquals("Error al buscar ingrediente, el ingrediente de id: 1, no se ha encontreado.", mes.message)
    }

    @Test
    fun getAll() {
        every { repositoryImpl.getAll() } returns ingredientesBase
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
        every { repositoryImpl.add(ingrediente) } returns ingrediente
        assertEquals(ingrediente, controller.add(ingrediente))
    }

    @Test
    fun addButAlreadyExisting(){
        every { repositoryImpl.add(ingredientesBase[0]) } returns null
        val mes = assertThrows<IngredienteAlreadyExistsException> { controller.add(ingredientesBase[0]) }
        assertEquals("Error al añadir ingrediente, el ingrediente de id: 1, ya existe.", mes.message)
    }
}