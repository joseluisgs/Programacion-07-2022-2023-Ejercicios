package validator

import exception.hamburgesaException.HamburgesaBadRequestException
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import model.Ingrediente
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import repository.Ingrediente.IngredienteRepositoryImpl
import storageService.Ingrediente.IngredienteStorageService

internal class ValidatorKtTest {

    @MockK
    private lateinit var storage: IngredienteStorageService

    @InjectMockKs
    private lateinit var repositoryImpl: IngredienteRepositoryImpl

    init{
        MockKAnnotations.init(this)
    }

    private val ingredientesBase = listOf<Ingrediente>(
        Ingrediente(1, "Cebolla", 25.6),
        Ingrediente(2, "Carne", 10.9),
        Ingrediente(3, "Patata", 13.4),
        Ingrediente(4, "Pan", 3.4),
        Ingrediente(5, "Lechuga", 1.99)
    )

    @Test
    fun validarListaDeIngredientesTest() {
        every { storage.loadAll() } returns ingredientesBase
        assertTrue(validarListaDeIngredientes(ingredientesBase.map { it.id }, repositoryImpl))
    }

    @Test
    fun validarListaDeIngredientesButInvalid() {
        every { storage.loadAll() } returns ingredientesBase
        val mes = assertThrows<HamburgesaBadRequestException>{validarListaDeIngredientes(listOf(5,6,7,8), repositoryImpl)}
        assertEquals("Error al introducir datos de la hamburgesa, el dato ingrediente de id = 6, no es valido.", mes.message)
    }

    @Test
    fun validateString() {
        assertTrue("EstoEsValido".validate(IllegalArgumentException()))
    }

    @Test
    fun validateStringButInvalid(){
        val mes = assertThrows<IllegalArgumentException> {"".validate(IllegalArgumentException("Error"))}
        assertEquals("Error", mes.message)
    }

    @Test
    fun validateNumber() {
        assertTrue("4".validateNumber(IllegalArgumentException(), 0))
        assertTrue("4.98".validateNumber(IllegalArgumentException(), 4.50))
    }

    @Test
    fun validateNumberButInvalid(){
        val mesInt1 = assertThrows<IllegalArgumentException> {"hola".validateNumber(IllegalArgumentException("Error"),0)}
        val mesInt2 = assertThrows<IllegalArgumentException> {"-1".validateNumber(IllegalArgumentException("Error"),0)}
        val mesDouble1 = assertThrows<IllegalArgumentException> {"hola".validateNumber(IllegalArgumentException("Error"),0.0)}
        val mesDouble2 = assertThrows<IllegalArgumentException> {"-1.0".validateNumber(IllegalArgumentException("Error"),0.0)}
        assertAll(
            { assertEquals("Error", mesInt1.message) },
            { assertEquals("Error", mesInt2.message) },
            { assertEquals("Error", mesDouble1.message) },
            { assertEquals("Error", mesDouble2.message) }
        )
    }

    @Test
    fun validateFecha() {
        assertTrue("2023-03-12".validateFecha(IllegalArgumentException()))
    }

    @Test
    fun validateFechaButInvalid() {
        val mes = assertThrows<IllegalArgumentException> {"".validate(IllegalArgumentException("Error"))}
        assertEquals("Error", mes.message)
    }
}