package repositories

import factories.ProductoFactory.getProductosDefault
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import models.Producto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import repository.producto.ProductoRepositoryMap
import services.storage.productos.ProductosStorageService

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class ProductoRepositoryMapTest: ProductoRepositoryGenericTest() {
    @MockK
    private lateinit var storageService: ProductosStorageService

    @InjectMockKs
    private lateinit var repo: ProductoRepositoryMap
    override fun getRepository() = repo

    @BeforeEach
    override fun setUp() {
        every { storageService.saveAll(emptyList()) } returns emptyList()
        every { storageService.saveAll(getProductosDefault()) } returns getProductosDefault()
        super.setUp()
    }

    @Test
    override fun findAllTest() {
        every { storageService.loadAll() } returns getProductosDefault()
        super.findAllTest()
        verify { storageService.loadAll() }
    }

    @Test
    override fun findByIdTest() {
        every { storageService.loadAll() } returns getProductosDefault()
        super.findByIdTest()
        verify { storageService.loadAll() }
    }

    @Test
    override fun createTest() {
        val expectation = getProductosDefault().toMutableList()
        expectation.add(getProductosDefault()[2].copy(id = 5, nombre = "NEW"))
        every { storageService.saveAll(expectation) } returns expectation
        every { storageService.loadAll() } returns expectation
        super.createTest()
        verify { storageService.saveAll(expectation) }
    }

    @Test
    override fun updateTest() {
        val expectation = getProductosDefault().toMutableList()
        expectation[1] = expectation[1].copy(nombre = "NEW")
        every { storageService.saveAll(expectation) } returns expectation
        every { storageService.loadAll() } returns expectation
        super.updateTest()
        verify { storageService.saveAll(expectation) }
    }

    @Test
    override fun deleteByIdTest() {
        val expectation = getProductosDefault().toMutableList()
        expectation.removeAt(0)
        every { storageService.saveAll(expectation) } returns expectation
        every { storageService.loadAll() } returns expectation
        super.deleteByIdTest()
        verify { storageService.saveAll(expectation) }
    }

    @Test
    override fun deleteByElement() {
        val expectation = getProductosDefault().toMutableList()
        expectation.removeAt(0)
        every { storageService.saveAll(expectation) } returns expectation
        every { storageService.loadAll() } returns expectation
        super.deleteByElement()
        verify { storageService.saveAll(expectation) }
    }

    @Test
    override fun existsByIdTest() {
        every { storageService.loadAll() } returns getProductosDefault()
        super.existsByIdTest()
        verify { storageService.loadAll() }
    }

    @Test
    override fun deleteAllTest() {
        every { storageService.saveAll(emptyList()) } returns emptyList()
        every { storageService.loadAll() } returns emptyList()
        super.deleteAllTest()
        verify { storageService.saveAll(emptyList()) }
    }
}