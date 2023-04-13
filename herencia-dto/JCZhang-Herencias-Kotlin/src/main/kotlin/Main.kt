import controllers.AlumnoController
import controllers.ProfesorController
import enums.modulos
import models.Alumno
import models.Persona
import models.Profesor
import okio.utf8Size
import repositories.AlumnoRepository
import repositories.ProfesorRepository
import storage.service.alumnos.AlumnoCsvService
import storage.service.profesores.ProfesorCsvService

fun main() {
    val profesorRepository = ProfesorRepository(ProfesorCsvService)
    val alumnoRepository = AlumnoRepository(AlumnoCsvService)

    val profesorController = ProfesorController(profesorRepository)
    val alumnoController = AlumnoController(alumnoRepository)

    profesorController.save()
    profesorController.load()

    alumnoController.save()
    alumnoController.load()

    val alumno1 = Alumno("Pedro", 22)
    val alumno2 = Alumno("María", 25)
    val alumno3 = Alumno("Carlos", 20)
    val alumno4 = Alumno("Ana", 23)
    val alumno5 = Alumno("Luis", 30)
    val alumno6 = Alumno("Sara", 26)
    val alumno7 = Alumno("Andrés", 19)
    val alumno8 = Alumno("Laura", 35)
    val alumno9 = Alumno("Diego", 21)
    val alumno10 = Alumno("Carmen", 28)
    val alumno11 = Alumno("Roberto", 24)
    val alumno12 = Alumno("Isabel", 27)
    val alumno13 = Alumno("Javier", 32)
    val alumno14 = Alumno("Patricia", 29)
    val alumno15 = Alumno("José", 36)
    val alumno16 = Alumno("Paula", 31)
    val alumno17 = Alumno("Raúl", 34)
    val alumno18 = Alumno("Silvia", 40)

    val alumnos = listOf(
        alumno1, alumno2, alumno3, alumno4, alumno5, alumno6, alumno7, alumno8, alumno9,
        alumno10, alumno11, alumno12, alumno13, alumno14, alumno15, alumno16, alumno17, alumno18
    )

    val profesor1 = Profesor("Pedro", modulos.PROGRAMACION)
    val profesor2 = Profesor("Olga", modulos.ENTORNOS)
    val profesores = listOf(profesor1, profesor2)

    val personas  = listOf(alumno1, alumno2, alumno3, alumno4, alumno5, alumno6, alumno7, alumno8, alumno9,
        alumno10, alumno11, alumno12, alumno13, alumno14, alumno15, alumno16, alumno17, alumno18, profesor1, profesor2)
    //Aquí comienzan las consultas

    //AlumnoMasJoven
    youngestStudent(alumnos)
    //media de edad de alumnos
    averageAgeOfStudents(alumnos)
    //Media de longitud de nombre
    averageNameSize(personas)
    //Listado de agrupados por tipo
    listOfPeopleGroupedByType(personas)


}

fun listOfPeopleGroupedByType(personas: List<Persona>): Map<String, List<Persona>> {
    val listOfPeopleGroupedByType = personas.groupBy { if (it is Profesor) "Profesores[${it}]" else "Alumnos[${it}]" }
    listOfPeopleGroupedByType.forEach{ println(it)}
    return listOfPeopleGroupedByType
}

fun averageNameSize(personas: List<Persona>): Double {
    val averageNameSize = personas.map { it.nombre.utf8Size() }.average()
    println(averageNameSize)
    return averageNameSize
}


fun averageAgeOfStudents(alumnos: List<Alumno>): Double {
    val averageAgeOfStudents = alumnos.map { it.edad }.average()
    println(averageAgeOfStudents)
    return averageAgeOfStudents
}

fun youngestStudent(alumnos: List<Alumno>): Alumno {
    val yougestStudent = alumnos.minBy { it.edad }
    println(yougestStudent)
    return yougestStudent
}






