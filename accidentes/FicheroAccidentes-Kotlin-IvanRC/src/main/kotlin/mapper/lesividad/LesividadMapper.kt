package mapper.lesividad

import dto.lesividad.LesividadDto
import model.Lesividad

fun Lesividad.toDto(): LesividadDto{
    return LesividadDto(
        id = if(this.id != null ) this.id.toString() else "NULL",
        lesividad = if(this.lesividad != null) this.lesividad else "NULL"
    )
}

fun LesividadDto.toLesividad(): Lesividad{
    return Lesividad(
        id = if(this.id.matches(Regex("[0-9]+"))) this.id.toInt() else null,
        lesividad = if(this.lesividad != "NULL" ) this.lesividad else null
    )
}