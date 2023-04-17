package factories

import models.Alumno
import models.Modulo
import models.Persona
import models.Profesor

fun claseRandom(): List<Persona>{
    val profesorProbability = 10.0
    val tamañoClase = 20.0
    val numProfesor = (tamañoClase / profesorProbability)
    val arrayNombres: Array<String> = arrayOf( "Jeevan", "Jeffrey", "Jensen", "Jenson", "Jensyn", "Jeremy", "Jerome", "Jeronimo", "Jerrick", "Jerry", "Jesse", "Jesuseun", "Jeswin", "Jevan", "Jeyun", "Jez", "Jia", "Jian", "Jiao", "Jimmy", "Jincheng", "JJ", "Joaquin", "Joash", "Jock", "Jody", "Joe", "Joeddy", "Joel", "Joey")
    val claseList: MutableList<Persona> = mutableListOf()

    for (i in 0 until numProfesor.toInt()) {
        val profesor = Profesor(name = arrayNombres.random(), modulo = Modulo.values().random().toString())
        claseList.add(profesor)
    }

    for (i in 0 until(tamañoClase - numProfesor).toInt()){
        val alumno = Alumno(name = arrayNombres.random(), edad = (18..40).random())
        claseList.add(alumno)
    }
    return claseList
}