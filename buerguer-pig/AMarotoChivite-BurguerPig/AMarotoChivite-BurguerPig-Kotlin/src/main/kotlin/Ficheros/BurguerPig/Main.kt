package Ficheros.BurguerPig

import Ficheros.BurguerPig.config.ConfigApp
import Ficheros.BurguerPig.controllers.ControllerBurguer
import Ficheros.BurguerPig.controllers.ControllerQueries
import Ficheros.BurguerPig.repositories.RepositoryBurguer
import Ficheros.BurguerPig.storages.factories.*
import kotlin.system.exitProcess

@ExperimentalStdlibApi // Tenemos que propagar la anotación
fun main() {
    // Cargamos la configuración general del 'properties' y 'LOCAL_FILE'
    ConfigApp.init()

    // Decidimos el tipo de fichero con el que trabajaremos
    val repositoryWithFilterStorage = decisionTypeStorage()

    // Controlador principal
    val controllerBurguer = ControllerBurguer(repositoryWithFilterStorage)
    // En primer lugar, cargamos todas las hamburguesas en el fichero seleccionado
    controllerBurguer.saveInFile()
    // Leemos las hamburguesas del fichero creado en el anterior paso
    val burguerListOfStorageReadFile = controllerBurguer.readInFile()

    // Controlador de las consultas con el tipo de storage ya decidido anteriormente
    val controllerQueries = ControllerQueries(burguerListOfStorageReadFile)

    println("1. Hamburguesa más cara")
    println(controllerQueries.burguerMoreExpensive())
    println()

    println("2. Hamburguesa con más ingredientes")
    println(controllerQueries.burguerMoreIngredients())
    println()

    println("3. Número de Hamburguesas por ingrediente")
    controllerQueries.numBurguerByIngredient().forEach {
        println("Ingrediente: ${it.key} -> Número Hamburguesas: ${it.value}")
    }
    println()

    println("4. Hamburguesas agrupadas por total de ingredientes")
    controllerQueries.burguersByIngredient().forEach { ingredient, burgers ->
        val burgerNames = burgers.joinToString(", ") { it.name }
        println("Ingrediente: $ingredient -> Hamburguesas: $burgerNames")
    }
    println()

    println("5. Precio medio de las hamburguesas")
    println(controllerQueries.averagePriceBurguers())
    println()

    println("6. Precio medio de los ingredientes")
    println(controllerQueries.averagePriceIngredients())
    println()
}

/**
 * Elección del tipo de Storage, para poder trabajar con un tipo de fichero determinado
 * @return el tipo de storage en particular con el que trabajaremos
 */
@ExperimentalStdlibApi // Tenemos que propagar la anotación
fun decisionTypeStorage(): RepositoryBurguer {
    while (true) {
        println("Elige el tipo de fichero con el que quieres realizar la gestión: ")
        println("")
        println("1: En Texto Plano")
        println("2: En Binario")
        println("3: En Serializado")
        println("4: En CSV")
        println("5: En MOSHI_JSON")
        println("6: En GSON_JSON")
        println("7: En XML")
        println("0: Salir")

        when (readln()) {
            "1" -> {
                return FactoryStorageBurguerTexto().create()
            }

            "2" -> {
                return FactoryStorageBurguerBinario().create()
            }

            "3" -> {
                return FactoryStorageBurguerSerializado().create()
            }

            "4" -> {
                return FactoryStorageBurguerCSV().create()
            }

            "5" -> {
                return FactoryStorageBurguerMOSHI_JSON().create()
            }

            "6" -> {
                return FactoryStorageBurguerGSON_JSON().create()
            }

            "7" -> {
                return FactoryStorageBurguerXML().create()
            }

            "0" -> {
                exitProcess(0)
            }
        }
    }
}
