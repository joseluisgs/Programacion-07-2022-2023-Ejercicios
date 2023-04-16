package Ficheros.HerenciasDto.controllers

import Ficheros.HerenciasDto.models.dto.PersonDTO

class ControllerQueries(private val listOfStorageReadFile: List<PersonDTO>) {

    private val listAlumno =
        listOfStorageReadFile.filter { it::class.simpleName == PersonDTO.Alumno::class.simpleName }
    private val listProfesor =
        listOfStorageReadFile.filter { it::class.simpleName == PersonDTO.Profesor::class.simpleName }

    fun profesorMoreAncient(): PersonDTO.Profesor? {
        if (listProfesor.isNotEmpty()) {
            return listProfesor.maxBy { it.edad } as PersonDTO.Profesor
        } else {
            return null
        }
    }

    fun alumnoLessAncient(): PersonDTO.Alumno {
        return listAlumno.minBy { it.edad } as PersonDTO.Alumno
    }

    fun averageAgeAlumno(): Double {
        return listAlumno.sumOf { it.edad.toDouble() } / listAlumno.size
    }

    fun averageLongitudeName(): Int {
        return listOfStorageReadFile.sumOf { it.name.length } / listAlumno.size
    }

    fun groupByType(): Map<String, List<PersonDTO>> {
        return listOfStorageReadFile
            .groupBy { it::class.simpleName.toString() }
            .mapValues { it ->
                it.value.map { it }
            }
    }
}