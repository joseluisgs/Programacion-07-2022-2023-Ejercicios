package mappers

import dto.AlumnoDTO
import dto.AlumnoListDto
import models.Alumno


fun Alumno.toDto() = AlumnoDTO(
    nombre = this.nombre,
    edad = this.edad
)

fun AlumnoDTO.toAlumno() = Alumno(
    nombre = nombre,
    edad = edad
)

fun List<Alumno>.alumnoListToDto() = AlumnoListDto(alumnos = map { it.toDto() })

fun AlumnoListDto.toAlumnoList() = alumnos.map { it.toAlumno() }
