import controller.PersonasController
import factories.claseRandom
import models.Persona
import repositories.persona.PersonasRepositoryList
import service.storage.persona.csv.PersonaFicheroCsvService
import service.storage.persona.json.PersonaFicheroJsonService
import service.storage.persona.xml.PersonasFicheroXmlService

@ExperimentalStdlibApi
fun main() {
    val personas = claseRandom()

    println("Personas Csv")
    personaCsv(personas)
    println()
    println("Personas Json")
    personaJson(personas)
    println()
    println("Personas Xml")
    personaXml(personas)

}

fun personaCsv(personas: List<Persona>) {
    val personasRepository = PersonasRepositoryList(PersonaFicheroCsvService)
    val personasController = PersonasController(personasRepository)

    personas.forEach { personasController.save(it) }

    val leerPersonas = personasController.findAll()

    leerPersonas.forEach { println(it)}
}

@ExperimentalStdlibApi
fun personaJson(personas: List<Persona>) {
    val personasRepository = PersonasRepositoryList(PersonaFicheroJsonService)
    val personasController = PersonasController(personasRepository)

    personas.forEach { personasController.save(it) }

    val leerPersonas = personasController.findAll()

    leerPersonas.forEach { println(it)}
}

fun personaXml(personas: List<Persona>) {
    val personasRepository = PersonasRepositoryList(PersonasFicheroXmlService)
    val personasController = PersonasController(personasRepository)

    personas.forEach { personasController.save(it) }

    val leerPersonas = personasController.findAll()

    leerPersonas.forEach { println(it)}

    println("El alumno mas joven: ${personasController.alumnoJoven()}")
    println("Media de la edad de los alumnos: ${personasController.mediaAlumnoEdad()}")
    println("Media de la longitud del nombre de las personas ${personasController.mediaNombreLogitud()}")
    println("Personas agrupados por tipo:")
    val groupByTipo = personasController.groupByTipo()
    groupByTipo.forEach { println(it) }
}
