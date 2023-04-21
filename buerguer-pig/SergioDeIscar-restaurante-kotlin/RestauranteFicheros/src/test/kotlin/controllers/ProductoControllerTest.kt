package controllers

import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import controller.producto.ProductoController
import errors.ProductoError
import factories.ProductoFactory.getProductosDefault
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import models.Bebida
import models.Hamburguesa
import models.Ingrediente
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith
import repository.producto.ProductoRepositoryMap
import services.storage.productos.ProductoFileCsv
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class ProductoControllerTest {
    @MockK
    private lateinit var storage: ProductoFileCsv
    @MockK
    private lateinit var repo: ProductoRepositoryMap

    @InjectMockKs
    private lateinit var controller: ProductoController

    private val ingredientesErrorId = listOf(
        Ingrediente(-1, "tomate", 2.2f),
        Ingrediente(2, "queso", 3.2f)
    )
    private val ingredientesErrorNombre = listOf(
        Ingrediente(1, "tomate", 2.2f),
        Ingrediente(2, "", 3.2f)
    )
    private val ingredientesErrorPrecio = listOf(
        Ingrediente(1, "tomate", -4.2f),
        Ingrediente(2, "queso", 3.2f)
    )
    private val ingredienteBien = (getProductosDefault()[0] as Hamburguesa).ingredientes

    @Test
    fun findAllTest(){
        every { repo.findAll() } returns getProductosDefault()
        assertEquals(
            getProductosDefault().size,
            controller.findAll().toList().size
        )
        verify { repo.findAll() }
    }

    @Test
    fun findByIdTest(){
        every { repo.findById(0) } returns getProductosDefault()[0]
        every { repo.findById(5) } returns null
        assertAll(
            { assertEquals(controller.findById(0).get(), getProductosDefault()[0]) },
            { assertTrue { controller.findById(5).getError() is ProductoError.ProductoNoEncontradoError } }
        )
        verify { repo.findById(0) }
        verify { repo.findById(5) }
    }

    @Test
    fun saveBebidaTest(){
        val bebidaErrorId = Bebida(-1, "Fanta", 1.5f, 255)
        val bebidaErrorNombre = Bebida(1, "", 1.5f, 255)
        val bebidaErrorPrecio = Bebida(1, "Fanta", -1.5f, 255)
        val bebidaErrorCapacidad = Bebida(1, "Fanta", 1.5f, -255)
        every { repo.save(getProductosDefault()[1]) } returns getProductosDefault()[1]
        assertAll(
            { assertEquals(controller.save(getProductosDefault()[1]).get(), getProductosDefault()[1]) },
            { assertTrue { controller.save(bebidaErrorId).getError() is ProductoError.IdError } },
            { assertTrue { controller.save(bebidaErrorNombre).getError() is ProductoError.NombreError } },
            { assertTrue { controller.save(bebidaErrorPrecio).getError() is ProductoError.PrecioError } },
            { assertTrue { controller.save(bebidaErrorCapacidad).getError() is ProductoError.CapacidadError } },
        )
        verify { repo.save(getProductosDefault()[1]) }
    }

    @Test
    fun saveHamburguesaTest(){
        val hamburguesaErrorId = Hamburguesa(-1, "Mediana", ingredienteBien)
        val hamburguesaErrorNombre = Hamburguesa(1, "", ingredienteBien)
        val hamburguesaErrorIngrediente = Hamburguesa(1, "Mediana", ingredientesErrorId)
        val hamburguesaErrorIngredientePrecio = Hamburguesa(1, "Mediana", ingredientesErrorPrecio)
        every { repo.save(getProductosDefault()[0]) } returns getProductosDefault()[0]
        assertAll(
            { assertEquals(controller.save(getProductosDefault()[0]).get(), getProductosDefault()[0]) },
            { assertTrue(controller.save(hamburguesaErrorId).getError() is ProductoError.IdError) },
            { assertTrue(controller.save(hamburguesaErrorNombre).getError() is ProductoError.NombreError) },
            { assertTrue(controller.save(hamburguesaErrorIngrediente).getError() is ProductoError.IngredienteError) },
            { assertTrue(controller.save(hamburguesaErrorIngredientePrecio).getError() is ProductoError.PrecioError) }
        )
    }

    @Test
    fun saveAllBebidaTest(){
        val productoErrorId = listOf(
            Bebida(-1, "Fanta", 2.5f, 500),
            Bebida(1, "Agua", 1f, 255),
        )
        val productoErrorNombre = listOf(
            Bebida(1, "Fanta", 2.5f, 500),
            Bebida(1, "", 1f, 255),
        )
        val productoErrorPrecio = listOf(
            Bebida(1, "Fanta", -2.5f, 500),
            Bebida(1, "Agua", 1f, 255),
        )
        val productoErrorCapacidad = listOf(
            Bebida(1, "Fanta", 2.5f, 500),
            Bebida(1, "Agua", 1f, -255),
        )
        every { repo.saveAll(getProductosDefault()) } returns Unit
        assertAll(
            { assertTrue { controller.saveAll(getProductosDefault()).get() != null } },
            { assertTrue { controller.saveAll(productoErrorId).getError() is ProductoError.IdError } },
            { assertTrue { controller.saveAll(productoErrorNombre).getError() is ProductoError.NombreError } },
            { assertTrue { controller.saveAll(productoErrorPrecio).getError() is ProductoError.PrecioError } },
            { assertTrue { controller.saveAll(productoErrorCapacidad).getError() is ProductoError.CapacidadError } }
        )
        verify { repo.saveAll(getProductosDefault()) }
    }

    @Test
    fun saveAllHamburguesaTest(){
        val hamburguesaErrorId = listOf(
            Hamburguesa(-1, "Mediana", ingredienteBien),
            getProductosDefault()[0]
        )
        val hamburguesaErrorNombre = listOf(
            Hamburguesa(1, "", ingredienteBien),
            getProductosDefault()[0]
        )
        val hamburguesaErrorIngredienteId = listOf(
            Hamburguesa(1, "Mediana", ingredientesErrorId),
            getProductosDefault()[0]
        )
        val hamburguesaErrorIngredienteNombre = listOf(
            Hamburguesa(1, "Mediana", ingredientesErrorNombre),
            getProductosDefault()[0]
        )
        val hamburguesaErrorPrecio = listOf(
            Hamburguesa(1, "Mediana", ingredientesErrorPrecio),
            getProductosDefault()[0]
        )
        every { repo.saveAll(getProductosDefault()) } returns Unit
        assertAll(
            { assertTrue { controller.saveAll(getProductosDefault()).get() != null } },
            { assertTrue { controller.saveAll(hamburguesaErrorId).getError() is ProductoError.IdError } },
            { assertTrue { controller.saveAll(hamburguesaErrorNombre).getError() is ProductoError.NombreError } },
            { assertTrue { controller.saveAll(hamburguesaErrorIngredienteId).getError() is ProductoError.IngredienteError } },
            { assertTrue { controller.saveAll(hamburguesaErrorIngredienteNombre).getError() is ProductoError.IngredienteError } },
            { assertTrue { controller.saveAll(hamburguesaErrorPrecio).getError() is ProductoError.PrecioError } }
        )
        verify { repo.saveAll(getProductosDefault()) }
    }

    @Test
    fun deleteByIdTest(){
        every { repo.deleteById(0) } returns true
        every { repo.deleteById(5) } returns false
        assertAll(
            { assertTrue { controller.deleteById(0).get() != null } },
            { assertTrue { controller.deleteById(5).getError() is ProductoError.ProductoNoEncontradoError } }
        )
        verify { repo.deleteById(0) }
        verify { repo.deleteById(5) }
    }

    @Test
    fun deleteTest(){
        every { repo.delete(getProductosDefault()[0]) } returns true
        every { repo.delete(getProductosDefault()[1]) } returns false // No existe
        assertAll(
            { assertTrue { controller.delete(getProductosDefault()[0]).get() != null } },
            { assertTrue { controller.delete(getProductosDefault()[1]).getError() is ProductoError.ProductoNoEncontradoError } }
        )
        verify { repo.delete(getProductosDefault()[0]) }
        verify { repo.delete(getProductosDefault()[1]) }
    }

    @Test
    fun existsByIdTest(){
        every { repo.existsById(0) } returns true
        every { repo.existsById(5) } returns false
        assertAll(
            { assertTrue { controller.existsById(0).get() != null } },
            { assertTrue { controller.existsById(5).getError() is ProductoError.ProductoNoEncontradoError } }
        )
        verify { repo.existsById(0) }
        verify { repo.existsById(5) }
    }
}