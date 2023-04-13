package mapper

import dto.ListaPersonasDto
import dto.PersonaDto
import exception.PersonaBadRequestException
import models.Alumno
import models.Persona
import models.Profesor
import mu.KotlinLogging

private val logger = KotlinLogging.logger {  }

fun Persona.toDto(): PersonaDto{
    logger.debug { "Se crea una personaDto, a partir de una persona." }
    var personaDto: PersonaDto? = null;
    if(this is Profesor){
        personaDto = PersonaDto(
            nombre = this.nombre,
            modulo = this.modulo,
            tipo = "profesor"
        )
    }else{
        if(this is Alumno) {
            personaDto = PersonaDto(
                nombre = this.nombre,
                edad = this.edad.toString(),
                tipo = "alumno"
            )
        }
    }
    return personaDto?: throw PersonaBadRequestException("Hubo algún error al tratar de crear a una personaDto.")
}

fun PersonaDto.toPersona(): Persona{
    logger.debug { "Se crea una persona, a partir de una personaDto." }
    var persona: Persona? = null
    if(this.tipo == "profesor"){
        persona = Profesor(
            nombre = this.nombre,
            modulo = this.modulo!!
        )
    }else{
        persona = Alumno(
            nombre = this.nombre,
            edad = this.edad!!.toInt()
        )
    }
    return persona
}

fun List<Persona>.toPersonasDto(): ListaPersonasDto{
    logger.debug { "Se crea una lista de personasDto, a partir de una lista de personas." }
    return ListaPersonasDto(
        this.map { it.toDto() }
    )
}

fun ListaPersonasDto.toPersonas(): List<Persona>{
    logger.debug { "Se crea una lista de personas, a partir de una lista de personasDto." }
    return this.personasDto.map { it.toPersona() }
}