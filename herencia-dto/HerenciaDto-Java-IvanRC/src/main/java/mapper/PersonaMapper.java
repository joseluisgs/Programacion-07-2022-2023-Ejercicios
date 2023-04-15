package mapper;

import dto.ListaPersonasDto;
import dto.PersonaDto;
import models.Alumno;
import models.Persona;
import models.Profesor;

import java.util.List;

public class PersonaMapper {

    public static PersonaDto toDto(Persona persona){
        PersonaDto personaDto = null;
        if(persona instanceof Profesor){
            personaDto = new PersonaDto(
                    persona.getNombre(),
                    null,
                    ((Profesor) persona).getModulo(),
                    "profesor"
            );
        }else{
            personaDto = new PersonaDto(
                    persona.getNombre(),
                    String.valueOf(((Alumno) persona).getEdad()),
                    null,
                    "alumno"
            );
        }
        return personaDto;
    }

    public static Persona toPersona(PersonaDto personaDto){
        Persona persona = null;
        if(personaDto.getTipo().equals("profesor")){
            persona = new Profesor(
                    personaDto.getNombre(),
                    personaDto.getModulo()
            );
        }else{
            persona = new Alumno(
                    personaDto.getNombre(),
                    Integer.parseInt(personaDto.getEdad())
            );
        }
        return persona;
    }

    public static ListaPersonasDto toPersonasDto(List<Persona> personas){
        return new ListaPersonasDto(
                personas.stream().map(PersonaMapper::toDto).toList()
        );
    }

    public static List<Persona> toPersonas(ListaPersonasDto personasDto){
        return personasDto.getPersonaDtos().stream().map(PersonaMapper::toPersona).toList();
    }

}
