import controllers.HamburguesasController
import controllers.IngredientesController
import models.Hamburguesa
import models.Ingrediente
import repositories.Hamburguesas.HamburguesasRepository
import repositories.Ingredientes.IngredientesRepository
import services.storage.hamburguesas.HamburguesaSerializableService
import services.storage.ingredientes.IngredienteSerService


@OptIn(ExperimentalStdlibApi::class)
fun main() {

    val ingredientesRepository = IngredientesRepository(IngredienteSerService)
    val hamburguesasRepository = HamburguesasRepository(HamburguesaSerializableService)


    val ingredientesController = IngredientesController(ingredientesRepository)
    val hamburguesasController = HamburguesasController(hamburguesasRepository)

    //Hamburguesas
    hamburguesasController.save()
    hamburguesasController.load()
    //Ingredientes
    ingredientesController.save()
    ingredientesController.load()


    val tomate = Ingrediente(nombre = "Tomate", precio = 0.75)
    val lechuga = Ingrediente(nombre = "Lechuga", precio = 0.50)
    val carne = Ingrediente(nombre = "Carne", precio = 2.50)
    val pepinillos = Ingrediente(nombre = "Pepinillos", precio = 0.35)
    val bacon = Ingrediente(nombre = "Bacon", precio = 1.25)
    val queso = Ingrediente(nombre = "Queso", precio = 1.00)

    val cheeseBurger = Hamburguesa(nombre = "CheeseBurger", ingredientes = listOf(carne, bacon, queso))
    val baconCrispyBurger =
        Hamburguesa(nombre = "BaconCrispyBurger", ingredientes = listOf(carne, bacon, queso, lechuga, pepinillos))
    val allInBurger =
        Hamburguesa(nombre = "AllInBurger", ingredientes = listOf(carne, tomate, lechuga, pepinillos, bacon, queso))
    val doubleCheeseBurger =
        Hamburguesa(nombre = "DoubleCheeseBurger", ingredientes = listOf(carne, carne, bacon, queso, queso))

    val ingredientes = listOf(tomate, lechuga, carne, pepinillos, bacon, queso)
    val hamburguesas = listOf(cheeseBurger, baconCrispyBurger, allInBurger, doubleCheeseBurger)

    //Hambuguesa más cara
    mostExpensiveBurger(hamburguesas)
    //Hambuguesa con más ingredientes
    burguerWithMostIngredients(hamburguesas)
    //Número de Hambuguesas por ingrediente (séase el número de hamburguesas que tiene cada ingrediente)
    numberOfBurguersPerIngredient(hamburguesas, ingredientes)
    //Hambuguesaas agrupadas por total de ingredientes
    buguersGroupedByNumberOfIngredientes(hamburguesas)
    // precio medio de las hamburguesas
    averagePriceBurger(hamburguesas)
    // precio medio de los ingredientes
    averagePriceIngrediente(ingredientes)


}




fun mostExpensiveBurger(hamburguesas: List<Hamburguesa>): Hamburguesa {
    val mostExpensiveBurger = hamburguesas.maxBy { it.precio }
    println(mostExpensiveBurger)
    return mostExpensiveBurger
}

fun burguerWithMostIngredients(hamburguesas: List<Hamburguesa>): Hamburguesa {
    val burguerWithMostIngredients = hamburguesas.maxBy { it.ingredientes.count() }
    println(burguerWithMostIngredients)
    return burguerWithMostIngredients

}


fun numberOfBurguersPerIngredient(hamburguesas: List<Hamburguesa>, ingredientes: List<Ingrediente>): Int {
    var numberOfBurguersPerIngredient: Int = 0
    for (i in ingredientes) {
        numberOfBurguersPerIngredient = hamburguesas.count { it.ingredientes.contains(i) }
        println("${i.nombre} = $numberOfBurguersPerIngredient")
    }
    return numberOfBurguersPerIngredient
}

fun buguersGroupedByNumberOfIngredientes(hamburguesas: List<Hamburguesa>): Map<Int, List<Hamburguesa>> {
    val buguersGroupedByNumberOfIngredientes = hamburguesas.groupBy { it.ingredientes.count() }
    println(buguersGroupedByNumberOfIngredientes)
    return buguersGroupedByNumberOfIngredientes
}

fun averagePriceBurger(hamburguesas: List<Hamburguesa>): Double {
    val averagePriceBurger = hamburguesas.map { it.precio }.average()
    println(averagePriceBurger)
    return averagePriceBurger
}

fun averagePriceIngrediente(ingredientes: List<Ingrediente>): Double {
    val averagePriceIngrediente = ingredientes.map { it.precio }.average()
    println(averagePriceIngrediente)
    return averagePriceIngrediente
}































