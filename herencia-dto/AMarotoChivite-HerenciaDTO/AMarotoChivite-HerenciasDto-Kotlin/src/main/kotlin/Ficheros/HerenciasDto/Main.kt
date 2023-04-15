package Ficheros.HerenciasDto

import Ficheros.HerenciaDto.controllers.Controller
import Ficheros.HerenciasDto.config.ConfigApp
import Ficheros.HerenciasDto.controllers.ControllerQueries
import Ficheros.HerenciasDto.repositories.Repository
import Ficheros.HerenciasDto.storage.factories.FactoryStorageCSV
import Ficheros.HerenciasDto.storage.factories.FactoryStorageGSON_JSON
import Ficheros.HerenciasDto.storage.factories.FactoryStorageMOSHI_JSON
import Ficheros.HerenciasDto.storage.factories.FactoryStorageXML
import kotlin.system.exitProcess

@ExperimentalStdlibApi
fun main() {
    // Cargamos la configuración general del 'properties' y 'LOCAL_FILE'
    ConfigApp.init()

    // Decidimos el tipo de fichero con el que trabajaremos
    val repositoryWithFilterStorage = decisionTypeStorage()

    // Controlador principal
    val controller = Controller(repositoryWithFilterStorage)
    // En primer lugar, cargamos todos los modelos en el fichero seleccionado
    controller.saveAllModelsInFileOverride()
    // Leemos los modelos del fichero creado en el anterior paso
    val listOfStorageReadFile = controller.readAllModelsInFile()

    // Controlador de las consultas con el tipo de storage ya decidido anteriormente
    val controllerQueries = ControllerQueries(listOfStorageReadFile)

    println("1. Profesor más anciano")
    println(controllerQueries.profesorMoreAncient())
    println()

    println("2. Alumno más joven")
    println(controllerQueries.alumnoLessAncient())
    println()

    println("3. Media de edad alumnos")
    println(controllerQueries.averageAgeAlumno())
    println()

    println("4. Media de longitud de nombre")
    println(controllerQueries.averageLongitudeName())
    println()

    println("5. Agrupo por tipo las personas")
    controllerQueries.groupByType().forEach { (type, list) ->
        val listaPersonas = list // .joinToString(", ") { it.name } // Si queremos solo los nombres
        println("Tipo: $type -> Lista: $listaPersonas")
    }
    println()
}

/**
 * Elección del tipo de Storage, para poder trabajar con un tipo de fichero determinado
 * @return el tipo de storage en particular con el que trabajaremos
 */
@ExperimentalStdlibApi
fun decisionTypeStorage(): Repository {
    while (true) {
        println("Elige el tipo de fichero con el que quieres realizar la gestión: ")
        println("")
        println("1: En CSV")
        println("2: En MOSHI_JSON")
        println("3: En XML")
        println("4: En GSON_JSON")
        println("0: Salir")

        when (readln()) {
            "1" -> {
                return FactoryStorageCSV().create()
            }

            "2" -> {
                return FactoryStorageMOSHI_JSON().create()
            }

            "3" -> {
                return FactoryStorageXML().create()
            }

            "4" -> {
                return FactoryStorageGSON_JSON().create()
            }

            "0" -> {
                exitProcess(0)
            }
        }
    }
}