package repositories

import models.Alumno
import storage.service.AlumnoStorageService

class AlumnoRepository(private val service: AlumnoStorageService) {

    private val alumno1 = Alumno("Pedro", 22)
    private val alumno2 = Alumno("María", 25)
    private val alumno3 = Alumno("Carlos", 20)
    private val alumno4 = Alumno("Ana", 23)
    private val alumno5 = Alumno("Luis", 30)
    private val alumno6 = Alumno("Sara", 26)
    private val alumno7 = Alumno("Andrés", 19)
    private val alumno8 = Alumno("Laura", 35)
    private val alumno9 = Alumno("Diego", 21)
    private val alumno10 = Alumno("Carmen", 28)
    private val alumno11 = Alumno("Roberto", 24)
    private val alumno12 = Alumno("Isabel", 27)
    private val alumno13 = Alumno("Javier", 32)
    private val alumno14 = Alumno("Patricia", 29)
    private val alumno15 = Alumno("José", 36)
    private val alumno16 = Alumno("Paula", 31)
    private val alumno17 = Alumno("Raúl", 34)
    private val alumno18 = Alumno("Silvia", 40)

    val alumnos = listOf(
        alumno1, alumno2, alumno3, alumno4, alumno5, alumno6, alumno7, alumno8, alumno9,
        alumno10, alumno11, alumno12, alumno13, alumno14, alumno15, alumno16, alumno17, alumno18
    )

    fun save(){
        service.saveAll(alumnos)
    }

    fun load(){
        service.loadAll()
    }
}