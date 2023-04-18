package repositories

import factories.ProductoFactory.getProductosDefault
import models.Bebida
import models.Hamburguesa
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import repository.producto.ProductoRepository
import java.lang.RuntimeException

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class ProductoRepositoryGenericTest {
    private lateinit var repository: ProductoRepository

    abstract fun getRepository(): ProductoRepository

    @BeforeEach
    open fun setUp() {
        repository = getRepository()
        repository.deleteAll()
        repository.saveAll(getProductosDefault())
        println()
    }

    @Test
    open fun findAllTest() {
        val productos = repository.findAll()
        assertAll(
            { assertEquals(getProductosDefault().size, repository.findAll().toList().size) },
            { assert(productos.toList()[0] is Hamburguesa) },
            { assert(productos.toList()[1] is Bebida) },
            { assert(productos.toList()[2] is Hamburguesa) },
            { assert(productos.toList()[3] is Bebida) },
        )
    }

    @Test
    open fun findByIdTest(){
        val result = repository.findById(1)
        val resultFail = repository.findById(5)
        assertAll(
            { kotlin.test.assertEquals(getProductosDefault()[0], result) },
            { kotlin.test.assertEquals(null, resultFail) },
        )
    }

    @Test
    open fun createTest(){
        val hamburguesa = getProductosDefault()[2].copy(id = -1, nombre = "NEW")
        repository.save(hamburguesa)
        assertAll(
            { kotlin.test.assertEquals(5, hamburguesa.id) },
            { kotlin.test.assertEquals(5, repository.findAll().toList().size) },
            { kotlin.test.assertEquals(hamburguesa, repository.findById(5)) },
        )
    }

    @Test
    open fun updateTest(){
        val bebida = getProductosDefault()[1].copy(nombre = "NEW")
        repository.save(bebida)
        assertAll(
            { kotlin.test.assertEquals(4, repository.findAll().toList().size) },
            { kotlin.test.assertEquals(bebida, repository.findById(2)) },
        )
    }

    @Test
    open fun deleteByIdTest(){
        repository.deleteById(1)
        assertAll(
            { kotlin.test.assertEquals(3, repository.findAll().toList().size) },
            { kotlin.test.assertEquals(null, repository.findById(1)) },
        )
    }

    @Test
    open fun deleteByElement(){
        repository.delete(getProductosDefault()[0])
        assertAll(
            { kotlin.test.assertEquals(3, repository.findAll().toList().size) },
            { kotlin.test.assertEquals(null, repository.findById(1)) },
        )
    }

    @Test
    open fun existsByIdTest(){
        assertAll(
            { kotlin.test.assertEquals(true, repository.existsById(1)) },
            { kotlin.test.assertEquals(false, repository.existsById(5)) },
        )
    }

    @Test
    open fun deleteAllTest(){
        repository.deleteAll()
        assertAll(
            { assertEquals(0, repository.findAll().toList().size) },
        )
    }
}