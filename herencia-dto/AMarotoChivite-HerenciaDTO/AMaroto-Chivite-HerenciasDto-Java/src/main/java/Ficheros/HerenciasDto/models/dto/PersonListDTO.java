package Ficheros.HerenciasDto.models.dto;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "listPersonas")
public class PersonListDTO {

    private List<PersonDTO> myList;

    @ElementList(name = "lista_alumnos", entry = "alumno", inline = true)
    private List<PersonDTO.Alumno> myListAlumno;

    @ElementList(name = "lista_profesores", entry = "profesor", inline = true)
    private List<PersonDTO.Profesor> myListProfesor;

    public PersonListDTO() {
    }

    public PersonListDTO(List<PersonDTO.Alumno> myListAlumno, List<PersonDTO.Profesor> myListProfesor) {
        this.myListAlumno = myListAlumno;
        this.myListProfesor = myListProfesor;
    }

    public List<PersonDTO> getMyList() {
        return myList;
    }

    public void setMyList(List<PersonDTO> myList) {
        this.myList = myList;
    }

    public List<PersonDTO.Alumno> getMyListAlumno() {
        return myListAlumno;
    }

    public void setMyListAlumno(List<PersonDTO.Alumno> myListAlumno) {
        this.myListAlumno = myListAlumno;
    }

    public List<PersonDTO.Profesor> getMyListProfesor() {
        return myListProfesor;
    }

    public void setMyListProfesor(List<PersonDTO.Profesor> myListProfesor) {
        this.myListProfesor = myListProfesor;
    }

}