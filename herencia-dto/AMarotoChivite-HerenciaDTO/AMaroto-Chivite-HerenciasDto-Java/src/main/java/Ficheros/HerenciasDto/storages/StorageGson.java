package Ficheros.HerenciasDto.storages;

import Ficheros.HerenciasDto.config.ConfigApp;
import Ficheros.HerenciasDto.models.dto.PersonDTO;
import Ficheros.HerenciasDto.models.dto.PersonListDTO;
import ch.qos.logback.classic.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StorageGson implements IStorageToWriteRead<PersonDTO> {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(StorageGson.class);
    private final String localFile = ConfigApp.APP_DATA + File.separator + "personGSON.json";


    @Override
    public void saveFile(List<PersonDTO> repository) {
        logger.debug("Storage: Escribiendo en JSON con GSON");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<PersonDTO.Alumno> listAlumno = repository.stream()
                .filter(personDTO -> personDTO instanceof PersonDTO.Alumno)
                .map(personDTO -> (PersonDTO.Alumno) personDTO)
                .collect(Collectors.toList());

        List<PersonDTO.Profesor> listProfesor = repository.stream()
                .filter(personDTO -> personDTO instanceof PersonDTO.Profesor)
                .map(personDTO -> (PersonDTO.Profesor) personDTO)
                .collect(Collectors.toList());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(localFile))) {
            writer.write(gson.toJson(new PersonListDTO(listAlumno, listProfesor)));
        } catch (IOException e) {
            logger.warn("Error de escritura");
        }
    }

    @Override
    public List<PersonDTO> readFile() {
        logger.debug("Storage: Leyendo desde JSON con GSON");

        // Primero leemos toda la lista
        String jsonString = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(localFile)); // Podríamos hacer try-with-resources
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            jsonString = stringBuilder.toString();
            bufferedReader.close(); // Podríamos hacer try-with-resources
        } catch (IOException e) {
            logger.warn("Error de lectura");
        }

        // Filtramos para diferenciar el tipo de objeto
        Gson gson = new Gson();
        PersonListDTO personListDTO = null;
        personListDTO = gson.fromJson(jsonString, PersonListDTO.class);

        List<PersonDTO.Alumno> listAlumno = personListDTO.getMyListAlumno();
        List<PersonDTO.Profesor> listProfesor = personListDTO.getMyListProfesor();

        List<PersonDTO> list = new ArrayList<>();
        if (listAlumno != null) {
            for (PersonDTO.Alumno alumno : listAlumno) {
                list.add(alumno); // Ya se asigna el polimorfismo al recorrer filtrando
            }
        }
        if (listProfesor != null) {
            for (PersonDTO.Profesor profesor : listProfesor) {
                list.add(profesor); // Ya se asigna el polimorfismo al recorrer filtrando
            }
        }
        return list;
    }
}

