package Ficheros.Accidentes

import Ficheros.Accidentes.config.ConfigApp
import Ficheros.Accidentes.controllers.ControllerPrincipal
import Ficheros.Accidentes.controllers.ControllerQueries
import Ficheros.Accidentes.models.dto.AccidenteDTO
import Ficheros.Accidentes.repositories.RepositoryToImportExport
import Ficheros.Accidentes.storage.factories.FactoryStorageGSON
import Ficheros.Accidentes.storage.factories.FactoryStorageMOSHI
import Ficheros.Accidentes.storage.factories.FactoryStorageXML
import kotlin.system.exitProcess

@ExperimentalStdlibApi
fun main() {
    ConfigApp.init()

    // Decidimos el tipo de fichero al que exportaremos
    val repositoryWithFilterStorageToExport = decisionTypeStorageToExport()
    // Controlador general donde exportaremos los datos ÚNICAMENTE (la importación está acoplada)
    val controllerPrincipal = ControllerPrincipal(repositoryWithFilterStorageToExport)
    controllerPrincipal.saveAllModelsInFile()
    val listModels: List<AccidenteDTO> = controllerPrincipal.readAllModelsInFile()

    // Consultas
    val controllerQueries = ControllerQueries(listModels)
    finalInformQueries(controllerQueries)
}

fun finalInformQueries(controllerQueries: ControllerQueries) {

    println("1. Accidentes en los que están implicados alcohol o drogas")
    // Para imprimir solo la cantidad y no ensuciar la consola
    println(controllerQueries.getAccidentesOnlyAlcoholOrDrugs().size)
    // Para imprimir todos los registros de accidentes
    // println(controllerQueries.getAccidentesOnlyAlcoholOrDrugs().joinToString("\n"))
    println()

    println("2. Numero de accidentes que han dado positivo en alcohol y drogas.")
    println(controllerQueries.numAccidentesOnlyAlcoholAndDrugs())
    println()

    println("3. Accidentes agrupados por sexo.")
    controllerQueries.accidentesBySexo().forEach {
        // Para imprimir solo la cantidad y no ensuciar la consola
        println("Sexo: ${it.key} -> Accidentes: ${it.value.size}")
        // Para imprimir todos los registros de accidentes
        // println("Sexo: ${it.key} -> Accidentes: ${it.value}")
    }
    println()

    println("4. Accidentes agrupados por meses.")
    controllerQueries.accidentesByMonths().forEach {
        // Para imprimir solo la cantidad y no ensuciar la consola
        println("Mes: ${it.key} -> Accidentes: ${it.value.size}")
        // Para imprimir todos los registros de accidentes
        // println("Mes: ${it.key} -> Accidentes: ${it.value}")
    }
    println()

    println("5. Accidentes agrupados por tipos de vehiculos.")
    controllerQueries.accidentesByVehiculos().forEach {
        // Para imprimir solo la cantidad y no ensuciar la consola
        println("Vehiculo: ${it.key} -> Accidentes: ${it.value.size}")
        // Para imprimir todos los registros de accidentes
        // println("Vehiculo: ${it.key} -> Accidentes: ${it.value}")
    }
    println()

    println("6. Accidentes ocurridos en la calle de leganes.")
    println(controllerQueries.accidentesByLocalizacion("leganes").joinToString("\n"))
    println()

    println("7. Numero de accidentes por distrito.")
    controllerQueries.numAccidentesByDistrito().forEach {
        println("Distrito: ${it.key} -> Accidentes: ${it.value}")
    }
    println()

    println("8. Estadisticas por distrito.")
    //println(controllerQueries.estadisticasByDistrito().joinToString("\n"))
    println()

    println("9. Accidentes por distrito ordenadas de manera descendente.")
    controllerQueries.accidentesByDistritoOrderDesc(3).forEach {
        println("Distrito: ${it.key} -> Accidentes: ${it.value}")
    }
    println()

    println("10. Listado de accidentes que se produzcan en fin de semana y de noche (a partir de las 8 de la tarde hasta las 6 de la mañana)")
    // Para imprimir solo la cantidad y no ensuciar la consola
    println(controllerQueries.accidentesEnFinDeSemanaAndNoche().size)
    // Para imprimir todos los registros de accidentes
    //println(controllerQueries.accidentesEnFinDeSemanaAndNoche().joinToString("\n"))
    println()

    println("11. Listado de accidentes que se produzcan en fin de semana y de noche donde se haya dado positivo en alcohol")
    println(controllerQueries.accidentesEnFinDeSemanaAndNocheAndPositiveAlcohol().joinToString("\n"))
    println()

    println("12. Accidentes donde haya fallecidos.")
    println(controllerQueries.accidentesWhereDead().joinToString("\n"))
    println()

    println("13. Comprobar si el distrito con mas accidentes coincide con el distrito donde hay más accidentes en fin de semana.")
    println("Coincidencia: ${controllerQueries.isDistritoMoreRiskySameAsDistritoMoreRiskyInWeekend().first}")
    println("Distrito Más Accidentes: ${controllerQueries.isDistritoMoreRiskySameAsDistritoMoreRiskyInWeekend().second}")
    println("Distrito Más Accidentes (fin de semana): ${controllerQueries.isDistritoMoreRiskySameAsDistritoMoreRiskyInWeekend().third}")
    println()

    println("14. Número de accidentes donde ha habido (alcohol o drogas) y victimas mortales.")
    println(controllerQueries.numAccidentesWherePositiveAlcholoOrDrugsAndDeads())
    println()

    println("15. Numero de accidentes donde ha habido atropellos a personas.")
    // Para imprimir solo la cantidad y no ensuciar la consola
    println(controllerQueries.numAccidentesWhereHitPeople().size)
    // Para imprimir todos los registros de accidentes
    //println(controllerQueries.numAccidentesWhereHitPeople().joinToString("\n"))
    println()

    println("16. Accidentes agrupados por estado metereológio.")
    controllerQueries.accidentesByMeteorologia().forEach {
        // Para imprimir solo la cantidad y no ensuciar la consola
        println("Meteorología: ${it.key} -> Accidentes: ${it.value.size}")
        // Para imprimir todos los registros de accidentes
        //println("Meteorología: ${it.key} -> Accidentes: ${it.value}")
    }
    println()

    println("17. Lista de accidentes donde haya habido un atropello animal.")
    println(controllerQueries.accidentesWhereHitAnimal().joinToString("\n"))
    println()
}

@ExperimentalStdlibApi
fun decisionTypeStorageToExport(): RepositoryToImportExport {
    while (true) {
        println("Elige el tipo de fichero al que quieres exportar los datos: ")
        println("1: En MOSHI_JSON")
        println("2: En GSON_JSON")
        println("3: En XML")
        println("0: Salir")

        when (readln()) {
            "1" -> {
                return FactoryStorageMOSHI().create()
            }

            "2" -> {
                return FactoryStorageGSON().create()
            }

            "3" -> {
                return FactoryStorageXML().create()
            }

            "0" -> {
                exitProcess(0)
            }
        }
    }
}