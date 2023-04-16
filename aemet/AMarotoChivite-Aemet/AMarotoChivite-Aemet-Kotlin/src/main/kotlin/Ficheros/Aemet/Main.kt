package Ficheros.Aemet

import Ficheros.Aemet.config.ConfigApp
import Ficheros.Aemet.controllers.ControllerPrincipal
import Ficheros.Aemet.controllers.ControllerQueries
import Ficheros.Aemet.controllers.ControllerToExport
import Ficheros.Aemet.models.Aemet
import Ficheros.Aemet.repositories.RepositoryToExport
import Ficheros.Aemet.repositories.RepositoryToImportExport
import Ficheros.Aemet.storage.factories.FactoryStorageGSON_JSON
import Ficheros.Aemet.storage.factories.FactoryStorageMOSHI_JSON
import Ficheros.Aemet.storage.factories.FactoryStorageXML
import Ficheros.Aemet.storages.StorageCSV
import java.io.File
import kotlin.system.exitProcess

@ExperimentalStdlibApi
fun main() {
    ConfigApp.init()

    // Lista de los items leídos desde el CSV en conjunto para poder exportar a XML o JSON
    // De esta menera nos ahorramos que JSON o XML lean, solo escriban desde esta lista!
    val listModels: List<Aemet>

    val controllerNest = ControllerPrincipal(RepositoryToImportExport(StorageCSV()))
    // Leemos los ficheros y sacamos la lista general de todos los datos en conjunto
    listModels = controllerNest.readInFile()

    // Comprobamos si existe ya el CSV en conjunto para optimizar tiempo
    val directory = File("${ConfigApp.APP_DATA}${File.separator}AemetConjuntoCSV.csv")
    if (!directory.exists()) {
        // Introducimos los datos leídos en el repository para poder crear un CSV
        controllerNest.saveInRepository(listModels)
        // Creamos el fichero CSV en conjunto
        controllerNest.saveInFile()
    }

    // Decidimos el tipo de fichero al que exportaremos
    val repositoryWithFilterStorageToExport = decisionTypeStorageToExport()
    // Controlador ÚNICAMENTE para exportar
    val controllerToExport = ControllerToExport(repositoryWithFilterStorageToExport)
    // Guardamos en el repositorio los datos leídos anteriormente para poder leerlo y exportar a XML y JSON
    controllerToExport.saveInRepository(listModels)
    // Exportamos
    val provincia = "Madrid"
    controllerToExport.saveInFileDecisionProvincia(provincia)

    // Consultas
    val controllerQueries = ControllerQueries(listModels)
    finalInformQueries(controllerQueries)
}

fun finalInformQueries(controllerQueries: ControllerQueries) {

    println("1.Temperatura máxima por día y lugar")
    controllerQueries.temperatureMaxPerDayAndLocate().forEach {
        println("Dia-Lugar: ${it.key} -> InfoTempMáx: ${it.value}")
    }
    println()

    println("2.Temperatura mínima por día y lugar")
    controllerQueries.temperatureMinPerDayAndLocate().forEach {
        println("Dia-Lugar: ${it.key} -> InfoTempMin: ${it.value}")
    }
    println()

    println("3.Temperatura máxima por provincia (día, lugar, valor y momento)")
    controllerQueries.temperatureMaxByProvincia().forEach {
        println("Provincia: ${it.key} -> InfoTempMax[día, lugar, valor y momento]: ${it.value}")
    }
    println()

    println("4.Temperatura mínima por provincia (día, lugar, valor y valor)")
    controllerQueries.temperatureMinByProvincia().forEach {
        println("Provincia: ${it.key[1]} -> InfoTempMin[día, lugar, valor y momento]: ${it.value}")
    }
    println()


    println("5.Temperatura media por provincia (día, lugar y valor)")
    controllerQueries.temperatureAverageByProvincia().forEach {
        println(
            "Provincia: ${it.key[1]} -> InfoTempAverage[día y valor]: ${it.key[0]}_${it.value}"
        )
    }
    println()

    println("6.Listado de precipitación media por día y provincia")
    println(controllerQueries.getListPrecipitacionAverageByDayAndProvincia().joinToString("\n"))
    println()

    println("7.Numero de lugares en el que llovíó por día y provincia")
    println(controllerQueries.numLocatesWasRainyByDayAndProvincia().joinToString("\n"))
    println()

    println("8.Temperatura media de la provincia de Madrid")
    println(controllerQueries.temperatureAverageFilterByOnePrivincia("Madrid").joinToString("\n"))
    println()

    println("9.Media de temperatura máxima total")
    println(controllerQueries.temperatureMaxAverage().joinToString("\n"))
    println()

    println("10.Media de temperatura minima total")
    println(controllerQueries.temperatureMinAverage().joinToString("\n"))
    println()

    println("11.Lugares donde la máxima temperatura ha sido antes de las 15:00 por día")
    println(controllerQueries.locatesWhereTempMaxWasBeforeAnHourByDay().joinToString("\n"))
    println()

    println("12.Lugares donde la mínima temperatura ha sido después de las 17:30 por día")
    println(controllerQueries.locatesWhereTempMinWasAfterAnHourByDay().joinToString("\n"))
    println()
}

/**
 * Elección del tipo de Storage, para poder trabajar con un tipo de fichero determinado
 * @return el tipo de storage en particular con el que trabajaremos
 */
@ExperimentalStdlibApi
fun decisionTypeStorageToExport(): RepositoryToExport {
    while (true) {
        println("Elige el tipo de fichero al que quieres exportar los datos: ")
        println("1: En MOSHI_JSON")
        println("2: En GSON_JSON")
        println("3: En XML")
        println("0: Salir")

        when (readln()) {
            "1" -> {
                return FactoryStorageMOSHI_JSON().create()
            }

            "2" -> {
                return FactoryStorageGSON_JSON().create()
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