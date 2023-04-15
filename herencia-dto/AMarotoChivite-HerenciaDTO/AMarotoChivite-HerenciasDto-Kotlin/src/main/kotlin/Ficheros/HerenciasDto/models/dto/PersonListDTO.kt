package Ficheros.HerenciasDto.models.dto

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "listPersonas") // Para XML
class PersonListDTO(
    var myListAlumno: List<PersonDTO.Alumno>? = null,
    var myListProfesor: List<PersonDTO.Profesor>? = null
) {
    constructor(
        myListAlumno: List<PersonDTO.Alumno>?, myListProfesor: List<PersonDTO.Profesor>?, myList: List<PersonDTO>
    ) : this(myListAlumno, myListProfesor) {
        this.myList = myList
    }

    @field:ElementList(name = "lista_personas", entry = "persona", inline = true) // Para XML
    var myList: List<PersonDTO>? = null // En el caso de XML no habría ningún problema podemos utilizar el general
}