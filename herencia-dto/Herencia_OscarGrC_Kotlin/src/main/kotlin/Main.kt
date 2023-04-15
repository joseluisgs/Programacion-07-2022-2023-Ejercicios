import Factories.PersonaFactory
import models.Alumno
import repositories.PersonaRepository

/*
Data esta estructura:

Persona(nombre: String)
Profesor(modulo: String): Persona
Alumno(edad: Int): Persona
Crear DTO y la estructura necesaria para pocesar información en CSV, JSON y XML
// Creo Clase AlumnoDTO y funcion de extension .toDTO

Para ello deberás crear 20 personas donde 10% son profesores y el resto alumnos. Los módulos puedeo ser "Programación" y "Entornos" y la edad va de los 18 a los 40.
//Creo un factory para esto.

Probar lectura y escritura de ficheros CSV, JSON y XML para estos ejemplo

Consultas:

Profesor más mayor
Alumno más joven
Media de edad de alumnos
Media de longitud de nombre
Listado de agrupados por tipo
 */
@ExperimentalStdlibApi
fun main(){

    val logger = mu.KotlinLogging.logger {}
        logger.info { "Ficheros Herencia " }
    // creamos una lista en el factory
    var lista = PersonaFactory.getInstance().lista20Personas()
    //ahora creamos un repositorio
    var repositorio = PersonaRepository()

    //En esta parte del codigo creo la lista de 0 y la guardo.
    //Si no queremos sobreescribir una y otra vez la lista quitar esta parte
    repositorio.repositorioList= lista
    //La salvamos en los diferentes formatos (Prueba)
    repositorio.whriteCSV()
    repositorio.whriteJSON()
    repositorio.whriteXML()
    //Probarlos con la primera consulta Alumno más mayor // la original es profesor. pero no tienen edad
    println("       CONSULTAS")
    println("       ---------")
    logger.info { "Alumno más mayor " }
    var alumnoViejo:Alumno = repositorio.repositorioList.filterIsInstance<Alumno>().maxBy { it.edad }
    println (alumnoViejo.toString())
    repositorio.repositorioList= repositorio.readCSV()
    alumnoViejo = repositorio.repositorioList.filterIsInstance<Alumno>().maxBy { it.edad }
    println (alumnoViejo.toString())
    repositorio.repositorioList= repositorio.readJSON()
    alumnoViejo = repositorio.repositorioList.filterIsInstance<Alumno>().maxBy { it.edad }
    println (alumnoViejo.toString())
    repositorio.repositorioList= repositorio.readXML()
    alumnoViejo = repositorio.repositorioList.filterIsInstance<Alumno>().maxBy { it.edad }
    println (alumnoViejo.toString())
    logger.info { "Alumno más joven " }
   val alumnoMasJoven= repositorio.repositorioList.filterIsInstance<Alumno>().minBy { it.edad }
    println(alumnoMasJoven)
    logger.info { "Media de edad de alumnos" }
    val mediaEdad:Double = repositorio.repositorioList.filterIsInstance<Alumno>().map { it.edad.toDouble() }.average()
    println("Edad Media:${mediaEdad} años.")
    logger.info { "Media longitud Nombres" }
    val mediaLongNombres:Int = repositorio.repositorioList.map { it.nombre.length }.average().toInt()
    println("Longuitud media de los nombres:${mediaLongNombres} letras.")
    logger.info { "Listado de agrupados por tipo" }
    val listaAgrupadaPorTipo = repositorio.repositorioList.groupBy { it::class }
    println(listaAgrupadaPorTipo)
}