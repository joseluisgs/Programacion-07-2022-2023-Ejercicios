package Ficheros.BurguerPig.storages;

import Ficheros.BurguerPig.config.ConfigApp;
import Ficheros.BurguerPig.mappers.BurguerListMapper;
import Ficheros.BurguerPig.mappers.BurguerMapper;
import Ficheros.BurguerPig.models.Burguer;
import Ficheros.BurguerPig.models.dto.BurguerDTO;
import Ficheros.BurguerPig.models.dto.BurguerListDto;
import ch.qos.logback.classic.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class StorageGson implements IStorageToWriteRead<Burguer> {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(StorageGson.class);

    private final String localFile = ConfigApp.APP_DATA + File.separator + "burguerGSON.json";

    @Override
    public void saveFile(List<Burguer> listItems) {
        logger.debug("Storage: Escribiendo en JSON con GSON");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(new BurguerListMapper(new BurguerMapper()).toDtoList(listItems).getBurguers());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(localFile))) {
            writer.write(jsonString);
        } catch (IOException e) {
            logger.warn("Error de escritura");
        }
    }

    @Override
    public List<Burguer> readFile() {
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
        Type type =  TypeToken.getParameterized(List.class, BurguerDTO.class).getType();
        return new BurguerListMapper(new BurguerMapper()).toModelList(new BurguerListDto(gson.fromJson(jsonString, type)));
    }
}

