package Ficheros.Aemet.storages;

import Ficheros.Aemet.config.ConfigApp;
import Ficheros.Aemet.models.Aemet;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StorageCSV implements IStorageToWriteRead<Aemet> {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(StorageCSV.class);

    private final String localFile = ConfigApp.APP_DATA + File.separator + "AemetConjuntoCSV.csv";

    @Override
    public void saveFileWithFilter(List<Aemet> listItems) {
        logger.debug("Storage: Escribiendo en CSV");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(localFile))) {
            writer.write("nombre_poblacion,nombre_provincia,temp_max,hora_temp_max,temp_min,hora_temp_min,precipitacion,fecha\n");
            listItems.forEach(it -> {
                try {
                    writer.write(it.getNombrePoblacion() + "," + it.getNombreProvincia() + "," + it.getTemperaturaMaxima().toString() + "," + it.getHoraTemperaturaMaxima().toString() + "," + it.getTemperaturaMinima().toString() + "," + it.getHoraTemperaturaMinima().toString() + "," + it.getPrecipitacion().toString() + "," + it.getDate().toString() + "\n");
                } catch (IOException e) {
                    logger.warn("Error de escritura", e);
                }
            });
        } catch (IOException e) {
            logger.warn("Error de escritura", e);
        }

    }

    @Override
    public List<Aemet> readFile() {
        logger.debug("Storage: Leyendo desde ficheros CSV");

        String folderName = "lecture";
        String folderPath = ConfigApp.APP_DATA + File.separator + folderName;
        File directory = new File(folderPath);

        File[] files = directory.listFiles((File file) -> {
            // Filtrar los archivos que tengan el patrón AemetYYYYMMDD.csv
            return file.getName().matches("Aemet\\d{8}\\.csv");
        });

        List<Aemet> listAemet = new ArrayList<Aemet>();

        // Por cada fichero leemos con bufferedReader con try-with-resources
        for (File file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                // Extraemos la fecha del nombre del archivo
                String fileNameDate = file.getName().substring(5, 13);
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                LocalDate date = LocalDate.parse(fileNameDate, dateFormatter);

                // Leemos cada línea
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(";");
                    String nombrePoblacion = fields[0];
                    String nombreProvincia = fields[1];
                    Double tempMax = Double.parseDouble(fields[2]);

                    // No lo puede parsear! Por ello con patrón aceptamos opcionalmente "h:mm" como "HH:mm"
                    DateTimeFormatter flexibleFormatter = DateTimeFormatter.ofPattern("[H:]mm[:ss][ a]");
                    LocalTime horaTempMax = LocalTime.parse(fields[3], flexibleFormatter);
                    Double tempMin = Double.parseDouble(fields[4]);
                    LocalTime horaTempMin = LocalTime.parse(fields[5], flexibleFormatter);

                    Double precipitacion = Double.parseDouble(fields[6]);
                    listAemet.add(new Aemet(nombrePoblacion, nombreProvincia, tempMax, horaTempMax, tempMin, horaTempMin, precipitacion, date));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listAemet;
    }
}

