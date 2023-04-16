package Ficheros.HerenciasDto.storages;

import Ficheros.HerenciasDto.config.ConfigApp;
import Ficheros.HerenciasDto.models.dto.PersonDTO;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class StorageCSV implements IStorageToWriteRead<PersonDTO> {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(StorageCSV.class);
    private final String localFile = ConfigApp.APP_DATA + File.separator + "personCSV.csv";
    File file = new File(localFile);

    @Override
    public void saveFile(List<PersonDTO> repository) {
        logger.debug("Storage: Escribiendo en CSV");

        // En primer lugar, intentamos crear el fichero
        try {
            file.createNewFile();
        } catch (IOException e) {
            logger.warn(e.getMessage(), e);
        }

        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("name,edad,modulo,type");
            repository.forEach(persona -> {
                if (persona instanceof PersonDTO.Alumno alumno) {
                    writer.println(alumno.getName() + "," + alumno.getEdad() + ",-,alumno");
                } else if (persona instanceof PersonDTO.Profesor profesor) {
                    writer.println(profesor.getName() + "," + profesor.getEdad() + "," + profesor.getModulo() + ",profesor");
                }
            });
        } catch (IOException e) {
            logger.error("Error al escribir en el archivo CSV: ", e);
        }
    }

    @Override
    public List<PersonDTO> readFile() {
        logger.debug("Storage: Leyendo desde CSV");

        List<PersonDTO> personas = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(new File(localFile).toPath());

            // Saltamos la primera línea del encabezado
            for (int i = 1; i < lines.size(); i++) {
                String[] fields = lines.get(i).split(",");

                String name = fields[0];
                String edad = fields[1];
                String type = fields[3];

                if (type.equals("alumno")) {
                    personas.add(new PersonDTO.Alumno(name, edad));
                } else if (type.equals("profesor")) {
                    String modulo = fields[2];
                    personas.add(new PersonDTO.Profesor(name, edad, modulo));
                }
            }
        } catch (IOException e) {
            logger.error("Error de lectura en el archivo CSV: ", e);
        }
        return personas;
    }
}


