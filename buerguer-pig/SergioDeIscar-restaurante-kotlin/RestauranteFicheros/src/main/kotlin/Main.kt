import config.AppConfig
import controller.HamburguesaController
import controller.PedidoController
import controller.ProductoController
import factories.HamburguesaFactory
import factories.ProductoFactory
import locate.toLocalMoney
import models.Hamburguesa
import models.Pedido
import models.Producto
import models.Usuario
import models.enums.UserRol
import repository.hamburguesa.HamburguesaRepositoryMap
import repository.pedido.PedidoRepositoryMap
import repository.productos.ProductoRepositoryMap
import services.storage.hamburguesa.*
import services.storage.pedido.PedidoFileXml
import services.storage.pedido.PedidoFileJson
import services.storage.productos.ProductoFileJson

fun main(args: Array<String>){
    println("APP_NAME: ${AppConfig.APP_NAME}")
    hamburguesasTest()
    //pedidosTest()
}

@OptIn(ExperimentalStdlibApi::class)
private fun pedidosTest(){
    val pedidoControllerJson = PedidoController(
        PedidoRepositoryMap(
            PedidoFileJson
        )
    )

    val pedidoControllerXml = PedidoController(
        PedidoRepositoryMap(
            PedidoFileXml
        )
    )

    val productoControllerJson = ProductoController(
        ProductoRepositoryMap(
            ProductoFileJson
        )
    )

    val user = Usuario(
        nombre = "root",
        apellido = "",
        password = "1234",
        rol = UserRol.ADMIN
    )

    val productos = mutableListOf<Producto>()
    repeat((5..10).random()){
        productos.add(ProductoFactory.getRdnProducto())
    }

    val lineasProducto = productos.map { it.toLineaPedido((1..10).random()) }

    val pedido = Pedido(user.id, lineasProducto)

    productoControllerJson.saveAll(productos)

    pedidoControllerJson.save(pedido)
    pedidoControllerXml.save(pedido)

    println("Pedidos:")
    pedidoControllerJson.getAll().forEach{ println(it) }
    pedidoControllerXml.getAll().forEach{ println(it) }

    println("Productos:")
    productoControllerJson.getAll().forEach{ println(it) }
}

@OptIn(ExperimentalStdlibApi::class)
private fun hamburguesasTest(){
    val hamburguesaControllerSeria = HamburguesaController(
        HamburguesaRepositoryMap(
            HamburguesaFileSerializable
        )
    )
    val hamburguesaControllerCsv = HamburguesaController(
        HamburguesaRepositoryMap(
            HamburguesaFileCSV
        )
    )
    val hamburguesaControllerBin = HamburguesaController(
        HamburguesaRepositoryMap(
            HamburguesaFileBinario
        )
    )
    val hamburguesaControllerAleatorio = HamburguesaController(
        HamburguesaRepositoryMap(
            HamburguesaFileAleatorio
        )
    )
    val hamburguesaControllerJson = HamburguesaController(
        HamburguesaRepositoryMap(
            HamburguesaFileJson
        )
    )

    val hamburguesasRdn = mutableListOf<Hamburguesa>()

    repeat((5..10).random()){
        hamburguesasRdn.add(HamburguesaFactory.getRdnHamburguesa())
    }

    hamburguesaControllerSeria.saveAll(hamburguesasRdn)
    hamburguesaControllerCsv.saveAll(hamburguesasRdn)
    hamburguesaControllerBin.saveAll(hamburguesasRdn)
    hamburguesaControllerAleatorio.saveAll(hamburguesasRdn)
    hamburguesaControllerJson.saveAll(hamburguesasRdn)

    println("Hamburguesas guardadas en el controlador serializable:")
    hamburguesaControllerSeria.getAll().forEach { println(it) }
    println("Hamburguesas guardadas en el controlador csv:")
    hamburguesaControllerCsv.getAll().forEach { println(it) }
    println("Hamburguesas guardadas en el controlador binario:")
    hamburguesaControllerBin.getAll().forEach { println(it) }
    println("Hamburguesas guardadas en el controlador aleatorio:")
    hamburguesaControllerAleatorio.getAll().forEach { println(it) }
    println("Hamburguesas guardadas en el controlador json:")
    hamburguesaControllerJson.getAll().forEach { println(it) }
    println("Los controladores tienen la misma info: " +
            (
                    hamburguesaControllerSeria.getAll() == hamburguesaControllerCsv.getAll() &&
                            hamburguesaControllerCsv.getAll() == hamburguesaControllerBin.getAll() &&
                            hamburguesaControllerBin.getAll() == hamburguesaControllerAleatorio.getAll() &&
                            hamburguesaControllerAleatorio.getAll() == hamburguesaControllerJson.getAll()
                    )
    )


    //Requisitos
    println("Hamburguesa mas cara:")
    hamburguesaControllerSeria.getHamburguesaMasCara()?.let { println(it) }
    println("Hamburguesa con mas ingredientes:")
    hamburguesaControllerSeria.getHamburguesaConMasIngredientes()?.let { println(it) }
    println("Precio medio:")
    println(hamburguesaControllerSeria.getPrecioMedio().toLocalMoney())
    println("Precio medio de ingredientes:")
    hamburguesaControllerSeria.getPrecioMedioIngredientes().mapValues { it.value.toLocalMoney() }.let { println(it) }
}