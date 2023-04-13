import factory.PersonaFactory
import models.Alumno
import models.Profesor
import storage.persona.PersonaStorageServiceCsv
import storage.persona.PersonaStorageServiceJson
import storage.persona.PersonaStorageServiceXml

@ExperimentalStdlibApi
fun main(args: Array<String>) {
    val personas = PersonaFactory.createPersonasRandom(20)

    println("Las personas generadas aleatoriamente son:")
    personas.forEach { println(it) }

    println()

    println("Ahora las guardo en el fichero JSON:")
    val json = PersonaStorageServiceJson()
    json.saveAll(personas)
    println()
    println("Volvemos a leer las personas del archivo JSON:")
    json.loadAll().forEach { println(it) }

    println()

    println("Ahora las guardo en el fichero XML:")
    val xml = PersonaStorageServiceXml()
    xml.saveAll(personas)
    println()
    println("Volvemos a leer las personas del archivo XML:")
    xml.loadAll().forEach { println(it) }

    println()

    println("Ahora las guardo en el fichero CSV:")
    val csv = PersonaStorageServiceCsv()
    csv.saveAll(personas)
    println()
    println("Volvemos a leer las personas del archivo CSV:")
    csv.loadAll().forEach { println(it) }

    println()

    println("Ahora empiezo con las consultas:")

    println()

    println("Profesor más mayor, pero como el profesor no tiene edad, cojo el profesor con el nombre más largo:")
    val profesor1 = personas.filterIsInstance<Profesor>().maxBy { it.nombre.length }
    println(profesor1)

    println()

    println("Alumno más joven:")
    val alumno1 = personas.filterIsInstance<Alumno>().maxBy { it.edad }
    println(alumno1)

    println()

    println("Media de edad de los alumnos:")
    val decimal1 = personas.filterIsInstance<Alumno>().map { it.edad }.average()
    println(decimal1)

    println()

    println("Media longitud de nombres:")
    val decimal2 = personas.map { it.nombre.length }.average()
    println(decimal2)

    println()

    println("Listado agrupados segun el tipo:")
    val mapa1 = personas.groupBy { if(it is Profesor) "Profesores" else "Alumnos" }
    mapa1.keys.forEach {
        println("$it--${mapa1[it]}")
    }
}