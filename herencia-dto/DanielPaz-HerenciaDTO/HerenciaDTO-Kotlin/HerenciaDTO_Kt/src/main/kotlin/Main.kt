import config.ConfigApp
import factories.personasFactory
import functions.*
import mappers.toDto
import services.storage.personas.PersonasCsvService
import services.storage.personas.PersonasJsonService
import services.storage.personas.PersonasXmlService


fun main() {

    println("APP_NAME: ${ConfigApp.APP_NAME}")

    val personas = personasFactory()

    PersonasCsvService.saveAll(personas)
    PersonasCsvService.loadAll()
    //println(personas)
    //println("\n\n\n")

    PersonasJsonService.saveAll(personas)
    PersonasJsonService.loadAll()
    //println(personas)
    //println("\n\n\n")

    PersonasXmlService.saveAll(personas)
    PersonasXmlService.loadAll()
    println(personas)
    println("\n\n\n")


    //Ahora hacemos las consultas:
    //Para facilitar saber que consulta es cada una, he hecho un menú

    do {

        menu()
        val respuesta:Int = leerRespuesta()

        when (respuesta){
            1 ->{ println( findJoven(personas) ) }
            2 ->{ println(findMayor(personas)) }
            3 ->{ println(mediaEdad(personas)) }
            4 ->{ println(mediaLongitudNombre(personas)) }
            5 ->{ println(groupTipo(personas)) }
            0 ->{ println("Hasta la próxima.") }
            else -> System.err.println("Opcion no encontrada!")
        }
    }while (respuesta!=0)
}