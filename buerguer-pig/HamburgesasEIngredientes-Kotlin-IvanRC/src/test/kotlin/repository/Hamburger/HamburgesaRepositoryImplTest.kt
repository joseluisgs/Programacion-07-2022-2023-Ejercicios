package repository.Hamburger

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import model.Hamburgesa
import model.Ingrediente
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import storageService.Hamburger.HamburgesaStorageService
import java.util.*

internal class HamburgesaRepositoryImplTest {

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
    private lateinit var storage: HamburgesaStorageService

    @InjectMockKs
    private lateinit var repositoryImpl: HamburgesaRepositoryImpl

    init{
        MockKAnnotations.init(this)
    }

    @BeforeEach
    fun setUp(){
        hamburgesas = hamburgesasBase.toMutableList()
    }

    @Test
    fun findById() {
        every { storage.loadAll() } returns hamburgesas
        assertEquals(hamburgesas[0], repositoryImpl.findById(UUID.fromString("420b4702-53ee-461a-9da1-66f676fd432e")))
    }

    @Test
    fun getAll() {
        every { storage.loadAll() } returns hamburgesas
        val lista = repositoryImpl.getAll()
        assertAll(
            { assertEquals(3, lista.size) },
            { assertEquals(hamburgesas[0], lista[0]) },
            { assertEquals(hamburgesas[1], lista[1]) },
            { assertEquals(hamburgesas[2], lista[2]) }
        )
    }

    @Test
    fun add() {
        val hamburgesa = Hamburgesa(UUID.fromString("5701f6fd-2d48-4849-8e79-f9c9145f85fe"), "Hamburgesa de bacon krispy", mutableListOf<Ingrediente>(ingredientesBase[1],ingredientesBase[4]))
        hamburgesas.add(hamburgesa)
        every { storage.loadAll() } returns hamburgesasBase
        every { storage.saveAll(hamburgesas) } returns Unit
        assertEquals(hamburgesa, repositoryImpl.add(hamburgesa))
    }
}