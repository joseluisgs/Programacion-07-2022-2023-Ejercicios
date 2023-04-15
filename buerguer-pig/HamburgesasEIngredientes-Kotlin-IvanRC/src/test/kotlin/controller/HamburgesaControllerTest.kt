package controller

import exception.hamburgesaException.HamburgesaAlreadyExistsException
import exception.hamburgesaException.HamburgesaNotFoundException
import exception.ingredienteException.IngredienteNotFoundException
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import model.Hamburgesa
import model.Ingrediente
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import repository.Hamburger.HamburgesaRepositoryImpl
import repository.Ingrediente.IngredienteRepositoryImpl
import storageService.Hamburger.HamburgesaStorageService
import java.util.*

internal class HamburgesaControllerTest {

    private val ingredientesBase = listOf<Ingrediente>(
        Ingrediente(1, "Cebolla", 25.6),
        Ingrediente(2, "Carne", 10.9),
        Ingrediente(3, "Patata", 13.4),
        Ingrediente(4, "Pan", 3.4),
        Ingrediente(5, "Lechuga", 1.99)
    )

    private val hamburgesasBase = listOf<Hamburgesa>(
        Hamburgesa(UUID.fromString("420b4702-53ee-461a-9da1-66f676fd432e"), "Hamburges de pollo", mutableListOf<Ingrediente>(ingredientesBase[0],ingredientesBase[3])),
        Hamburgesa(UUID.fromString("157e75c6-b966-426e-8c8d-7a6bfe0681fa"), "Hamburges de carne", mutableListOf<Ingrediente>(ingredientesBase[1],ingredientesBase[4])),
        Hamburgesa(UUID.fromString("7c9fff49-ff67-446f-a4bd-bdcb7f72034f"), "Hamburges vegetariana", mutableListOf<Ingrediente>(ingredientesBase[2],ingredientesBase[2]))
    )

    private var hamburgesas = mutableListOf<Hamburgesa>()

    @MockK
    private lateinit var hamburgesaRepositoryImpl: HamburgesaRepositoryImpl

    @MockK
    private lateinit var ingredienteRepositoryImpl: IngredienteRepositoryImpl

    @InjectMockKs
    private lateinit var controller: HamburgesaController

    init{
        MockKAnnotations.init(this)
    }

    @BeforeEach
    fun setUp(){
        hamburgesas = hamburgesasBase.toMutableList()
    }

    @Test
    fun findById() {
        every { hamburgesaRepositoryImpl.findById(UUID.fromString("420b4702-53ee-461a-9da1-66f676fd432e")) } returns hamburgesasBase[0]
        assertEquals(hamburgesasBase[0], controller.findById("420b4702-53ee-461a-9da1-66f676fd432e"))
    }

    @Test
    fun findByIdBuNotFound() {
        every { hamburgesaRepositoryImpl.findById(UUID.fromString("420b4702-53ee-461a-9da1-66f676fd432e")) } returns null
        val mes = org.junit.jupiter.api.assertThrows<HamburgesaNotFoundException> { controller.findById("420b4702-53ee-461a-9da1-66f676fd432e") }
        assertEquals("Error al buscar hamburgesa, la hamburgesa de id: 420b4702-53ee-461a-9da1-66f676fd432e, no se ha encontrado.", mes.message)
    }

    @Test
    fun getAll() {
        every { hamburgesaRepositoryImpl.getAll() } returns hamburgesasBase
        val lista = hamburgesaRepositoryImpl.getAll()
        assertAll(
            { assertEquals(3, lista.size) },
            { assertEquals(hamburgesasBase[0], lista[0]) },
            { assertEquals(hamburgesasBase[1], lista[1]) },
            { assertEquals(hamburgesasBase[2], lista[2]) },
        )
    }

    @Test
    fun add(){
        val hamburgesa = Hamburgesa(UUID.fromString("5701f6fd-2d48-4849-8e79-f9c9145f85fe"), "Hamburgesa de bacon krispy", mutableListOf<Ingrediente>(ingredientesBase[1],ingredientesBase[4]))
        hamburgesas.add(hamburgesa)
        every { hamburgesaRepositoryImpl.add(hamburgesa) } returns hamburgesa
        assertEquals(hamburgesa, controller.add(hamburgesa))
    }

    @Test
    fun addButAlreadyExisting() {
        every { hamburgesaRepositoryImpl.add(hamburgesasBase[0]) } returns null
        val mes = assertThrows<HamburgesaAlreadyExistsException> {controller.add(hamburgesasBase[0])}
        assertEquals("Error al añadir hamburgesa, la hamburgesa de id: 420b4702-53ee-461a-9da1-66f676fd432e, ya existe.", mes.message)
    }
}