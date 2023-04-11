package mapper.distrito

import dto.distrito.DistritoDto
import model.Distrito

fun Distrito.toDto(): DistritoDto{
    return DistritoDto(
        id = if(this.id != null) this.id.toString() else "NULL",
        nombre = this.nombre
    )
}

fun DistritoDto.toDistrito(): Distrito{
    return Distrito(
        id = if(this.id.matches(Regex("[0-9]+"))) this.id.toInt() else null,
        nombre = this.nombre
    )
}