package org.example.services.storage.persona;

import org.example.config.AppConfig;
import org.example.models.Alumno;
import org.example.models.Persona;
import org.example.models.Profesor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonaFileCsv implements PersonaStorageService{
    //Singleton
    private static PersonaFileCsv instance = null;
    public static PersonaFileCsv getInstance() {
        if (instance == null) instance = new PersonaFileCsv();
        return instance;
    }

    private final String localFile = AppConfig.getInstance().getAppData() + File.separator + "personas.csv";
    @Override
    public List<Persona> saveAll(List<Persona> elements) {
        File file = new File(localFile);
        if (file.exists() && !file.canWrite() ) return null;
        try(PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            pw.println("id,nombre,tipo,edad,modulo");
            elements.forEach(e ->
                pw.println(
                        e.getId() + "," +
                        e.getNombre() + "," +
                        (e instanceof Alumno ? "Alumno" : "Profesor") + "," +
                        (e instanceof Alumno ? ((Alumno) e).getEdad() : "null" + "," +
                        (e instanceof Profesor ? ((Profesor) e).getModulo() : "null"))
                )
            );
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo");
        }
        return elements;
    }

    @Override
    public List<Persona> loadAll() {
        File file = new File(localFile);
        if(!file.exists() || !file.canRead()) return null;
        try(Stream<String> stream = Files.lines(file.toPath())) {
            return stream.skip(1)
                    .map(row -> row.split(","))
                    .map(linea ->
                        switch (linea[2])
                        {
                            case "Alumno" -> new Alumno(Integer.parseInt(linea[0]), linea[1], Integer.parseInt(linea[3]));
                            case "Profesor" -> new Profesor(Integer.parseInt(linea[0]), linea[1], linea[4]);
                            default -> throw new RuntimeException("Tipo de persona no reconocido");
                        }
                    ).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }
        return null;
    }
}
