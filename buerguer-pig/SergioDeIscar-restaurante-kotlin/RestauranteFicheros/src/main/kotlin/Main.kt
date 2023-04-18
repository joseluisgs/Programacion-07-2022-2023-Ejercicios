import config.AppConfig
import controller.producto.ProductoController
import factories.ProductoFactory
import models.Producto
import repository.producto.ProductoRepositoryDataBase
import repository.producto.ProductoRepositoryMap
import services.database.DataBaseManager
import services.storage.productos.ProductoFileJson

@ExperimentalStdlibApi
fun main(){
    val productos = ProductoFactory.getRdnProductos()

    val controllers = listOf(
        ProductoController(
            ProductoRepositoryDataBase
        ),
        ProductoController(
            ProductoRepositoryMap(
                ProductoFileJson
            )
        )
    )

    // Por ahora da false, pero es por el orden de las listas y por los decimales de los precios
    println("Tienen el mismo contenido:" + generateFile(productos, controllers))
}

private fun generateFile(personas: List<Producto>, controllers: List<ProductoController>): Boolean {
    controllers.forEach { it.saveAll(personas) }
    return controllers.all { it.findAll() == personas }
}