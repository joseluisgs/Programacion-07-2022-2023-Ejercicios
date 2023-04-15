package Ficheros.Aemet.storages;

import Ficheros.Aemet.config.ConfigApp;
import Ficheros.Aemet.models.Aemet;
import Ficheros.Aemet.models.AemetDailyConsultToExport;
import Ficheros.Aemet.utils.GroupDayWithFilter;
import ch.qos.logback.classic.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class StorageGson implements IStorageToWrite<Aemet> {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(StorageGson.class);

    private final String localFile = ConfigApp.APP_DATA + File.separator + "AemetDaily_GSON.json";

    /**
     * Escribimos el JSON el mapa POR CADA DIA con las consultas a continuación
     * - Temperatura media
     * - Temperatura máxima (Lugar y momento)
     * - Temperatura mínima (Lugar y momento)
     * - Si hubo precipitación (sí/no) y valor de la misma.
     */
    @Override
    public void saveFileWithFilter(List<Aemet> listItems) {
        logger.debug("Storage: Escribiendo en JSON con GSON");

        Map<String, AemetDailyConsultToExport> dailyWeatherMap = new GroupDayWithFilter().create(listItems);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(dailyWeatherMap);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(localFile))) {
            writer.write(jsonString);
        } catch (IOException e) {
            logger.warn("Error de escritura");
        }
    }
}

