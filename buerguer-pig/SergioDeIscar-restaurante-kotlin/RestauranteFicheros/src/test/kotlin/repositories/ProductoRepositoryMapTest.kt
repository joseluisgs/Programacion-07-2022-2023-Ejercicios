package repositories

import org.junit.jupiter.api.TestInstance
import repository.producto.ProductoRepositoryMap

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductoRepositoryMapTest: ProductoRepositoryGenericTest() {
    private var repository = ProductoRepositoryMap
    override fun getRepository() = repository

}