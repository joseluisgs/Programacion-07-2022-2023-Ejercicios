package org.example.services.storage.dupla;

import org.example.models.Dupla;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.example.formateadores.Formateador.toLocalTimeFormate;

public class DuplaFileCsv implements DuplaStorageService{
    //Singleton
    private static DuplaFileCsv instance = null;
    public static DuplaFileCsv getInstance(){
        if (instance == null) instance = new DuplaFileCsv();
        return instance;
    }

    @Override
    public List<Dupla> saveAll(List<Dupla> elements) {
        // Por ahora no se va a escribir en el fichero
        return elements;
    }

    @Override
    public List<Dupla> loadAll() {
        List<Dupla> allDuplas = new ArrayList<>();
        allDuplas.addAll(loadOneFile("Aemet20171029"));
        allDuplas.addAll(loadOneFile("Aemet20171030"));
        allDuplas.addAll(loadOneFile("Aemet20171031"));
        return allDuplas;
    }

    private List<Dupla> loadOneFile(String name){
        InputStream duplasCsv = DuplaFileCsv.class.getResourceAsStream("/" + name + ".csv");
        if (duplasCsv == null) throw new RuntimeException("Error al cargar el CSV o el fichero no existe");

        List<Dupla> duplas = new ArrayList<>();

        LocalDate date = LocalDate.parse(
                name.substring(5, 9) + "-" + name.substring(9, 11) + "-" + name.substring(11, 13)
        );

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(duplasCsv))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(";");
                duplas.add(
                        new Dupla(
                                row[0],
                                row[1],
                                Double.parseDouble(row[2]),
                                toLocalTimeFormate(row[3]),
                                Double.parseDouble(row[4]),
                                toLocalTimeFormate(row[5]),
                                Double.parseDouble(row[6]),
                                date
                        )
                );
            }
        }catch (IOException e){
            System.out.println("Error al leer el fichero");
        }

        return duplas;
    }
}
