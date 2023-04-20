import config.AppConfig
import controller.producto.ProductoController
import factories.ProductoFactory
import models.Producto
import repository.producto.ProductoRepositoryDataBase
import repository.producto.ProductoRepositoryMap
import services.database.DataBaseManager
import services.storage.productos.ProductoFileCsv
import services.storage.productos.ProductoFileJson
import services.storage.productos.ProductoFileXml

@ExperimentalStdlibApi
fun main(){
    val productos = ProductoFactory.getRdnProductos()

    val controllers = listOf(
        ProductoController(
            ProductoRepositoryDataBase,
            ProductoFileCsv
        ),
        ProductoController(
            ProductoRepositoryMap,
            ProductoFileJson
        ),
        ProductoController(
            ProductoRepositoryMap,
            ProductoFileXml
        ),
        ProductoController(
            ProductoRepositoryMap,
            ProductoFileCsv
        )
    )

    // Por ahora da false, pero es por el orden de las listas y por los decimales de los precios
    println("Tienen el mismo contenido:" + generateFile(productos, controllers))
}

private fun generateFile(personas: List<Producto>, controllers: List<ProductoController>): Boolean {
    controllers.forEach { it.saveAll(personas) }
    controllers.forEach{ it.exportData() }
    controllers.forEach{ it.importData() }
    return controllers.all { it.findAll() == personas }
}