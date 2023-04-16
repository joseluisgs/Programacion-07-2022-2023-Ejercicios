package Ficheros.Accidentes.storages;

import Ficheros.Accidentes.config.ConfigApp;
import Ficheros.Accidentes.models.dto.AccidenteDto;
import Ficheros.Accidentes.utils.ReadDataOfFile;
import ch.qos.logback.classic.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class StorageGson implements IStorageToWriteRead<AccidenteDto> {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(StorageGson.class);

    private final String localFile = ConfigApp.APP_DATA + File.separator + "AccidentesGSON_JSON.json";

    @Override
    public void saveInFileWithFilter() {
        logger.debug("Storage: Escribiendo en JSON con GSON");

        List<AccidenteDto> listToExport = new ReadDataOfFile().readDataOfCSV();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(listToExport);

        // try-with-resources, es decir, el BUFFERED se cierra automáticamente
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(localFile))) {
            writer.write(jsonString);
        } catch (IOException e) {
            logger.warn("Error de escritura");
        }
    }

    @Override
    public List<AccidenteDto> readAllModelsInFile() {
        logger.debug("Storage: Leyendo desde JSON con GSON");

        Gson gson = new Gson();
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
        Type type = TypeToken.getParameterized(List.class, AccidenteDto.class).getType();
        return gson.fromJson(jsonString, type);
    }
}

