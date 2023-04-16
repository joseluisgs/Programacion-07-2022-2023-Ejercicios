package Ficheros.HerenciasDto.controllers;

import Ficheros.HerenciasDto.models.dto.PersonDTO;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerQueries {

    private final List<PersonDTO> listOfStorageReadFile;
    private List<PersonDTO.Profesor> listProfesor;
    private List<PersonDTO.Alumno> listAlumno;

    public ControllerQueries(List<PersonDTO> listOfStorageReadFile) {
        this.listOfStorageReadFile = listOfStorageReadFile;
        initStorages();
    }

    private void initStorages() {
        this.listAlumno = listOfStorageReadFile.stream()
                .filter(personDTO -> personDTO instanceof PersonDTO.Alumno)
                .map(personDTO -> (PersonDTO.Alumno) personDTO)
                .collect(Collectors.toList());

        this.listProfesor = listOfStorageReadFile.stream()
                .filter(personDTO -> personDTO instanceof PersonDTO.Profesor)
                .map(personDTO -> (PersonDTO.Profesor) personDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO.Profesor profesorMoreAncient() {
        if (!listProfesor.isEmpty()) {
            return Collections.max(listProfesor, Comparator.comparing(PersonDTO::getEdad));
        } else {
            return null;
        }
    }

    public PersonDTO.Alumno alumnoLessAncient() {
        return Collections.min(listAlumno, Comparator.comparing(PersonDTO::getEdad));
    }

    public double averageAgeAlumno() {
        return listAlumno.stream().mapToInt(PersonDTO::getEdad).average().orElse(Double.NaN);
    }

    public int averageLongitudeName() {
        return (int) listOfStorageReadFile.stream().mapToInt(personDTO -> personDTO.getName().length()).average().orElse(Double.NaN);
    }

    public Map<String, List<PersonDTO>> groupByType() {
        return listOfStorageReadFile.stream().collect(Collectors.groupingBy(personDTO -> personDTO.getClass().getSimpleName()));
    }
}
