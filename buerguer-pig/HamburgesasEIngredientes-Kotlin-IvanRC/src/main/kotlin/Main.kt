import config.ConfigApp
import controller.HamburgesaController
import controller.IngredienteController
import factory.HamburgesaFactory
import factory.IngredienteFactory
import mapper.toDto
import model.Ingrediente
import org.simpleframework.xml.core.Persister
import repository.Hamburger.HamburgesaRepositoryImpl
import repository.Ingrediente.IngredienteRepositoryImpl
import storageService.Hamburger.HamburgesaStorageServiceJSON
import storageService.Hamburger.HamburgesaStorageServiceXML
import storageService.Ingrediente.IngredienteStorageServiceBinario
import storageService.Ingrediente.IngredienteStorageServiceJSON
import storageService.Ingrediente.IngredienteStorageServiceTexto
import storageService.Ingrediente.IngredienteStorageServiceXML
import java.io.File
import java.util.*

@OptIn(ExperimentalStdlibApi::class)
fun main(args: Array<String>) {



    val ingredienteRepository = IngredienteRepositoryImpl(IngredienteStorageServiceJSON())
    val ingredienteController = IngredienteController(ingredienteRepository)
    val hamburgesaController = HamburgesaController(HamburgesaRepositoryImpl(HamburgesaStorageServiceJSON()), ingredienteRepository)


    val ingredientesRandom = IngredienteFactory.getInstance().createSomeRandom()
    ingredientesRandom.forEach {
        //ingredienteController.add(it)
    }

    val ingredientes = ingredienteController.getAll()
    ingredientes.forEach { println(it) }
    println()
    //println(ingredienteController.findById("3"))
    println()

    repeat(4){
        //hamburgesaController.add(HamburgesaFactory.createRandom(ingredientes))
    }

    val hamburgesas = hamburgesaController.getAll()
    hamburgesas.forEach { println(it) }
    println()
    //println(hamburgesaController.findById("9e93c3cc-8526-4ba8-a62e-a9f33c464bc8"))

    println("Empezamos con las consultas:")
    println()

    println("La haburgesa más cara:")
    val hamburgesa1 = hamburgesas.maxBy { it.precio }
    println(hamburgesa1)
    println()

    println("La haburgesa con más ingredientes:")
    val hamburgesa2 = hamburgesas.maxBy { it.ingredientes.size }
    println(hamburgesa2)
    println()

    println("Numero de hamburgesas por ingrediente:")
    val mapa1 = ingredientes.groupBy {it.nombre }.mapValues { key -> hamburgesas.filter { it.ingredientes.map { it.nombre }.contains(key.key) }.size }
    mapa1.keys.forEach{
        println("$it--${mapa1[it]}")
    }
    println()

    println("Hamburgesas agrupadas por total de ingredientes:")
    println("Primera opcion:")
    val mapa2 = hamburgesas.groupBy { it.ingredientes }
    mapa2.keys.forEach{
        println("$it:\n${mapa2[it]}\n")
    }
    println()
    println("Segunda opción:")
    val mapa3 = hamburgesas.groupBy { it.precio }
    mapa3.keys.forEach{
        println("$it--${mapa3[it]}")
    }
    println()

    println("Precio medio de las hamburgesas:")
    val decimal1 = hamburgesas.map { it.precio }.average()
    println(decimal1)
    println()

    println("Precio medio de los ingredientes:")
    val decimal2 = ingredientes.map { it.precio }.average()
    println(decimal2)
    println()
}

