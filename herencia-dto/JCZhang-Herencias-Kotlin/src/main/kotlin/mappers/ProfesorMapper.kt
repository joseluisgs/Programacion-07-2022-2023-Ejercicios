package mappers

import dto.ProfesorDTO
import dto.ProfesorListDto
import models.Profesor

fun Profesor.toDto() = ProfesorDTO(
    nombre = this.nombre,
    modulo = this.modulo.toString()
)

fun List<Profesor>.profesorListToDto() =ProfesorListDto(
    profesores = map{it.toDto() }
)

fun ProfesorDTO.toProfesor() = Profesor(
    nombre = nombre,
    modulo = enumValueOf(modulo)
)

fun ProfesorListDto.toProfesorList() = profesores.map { it.toProfesor() }