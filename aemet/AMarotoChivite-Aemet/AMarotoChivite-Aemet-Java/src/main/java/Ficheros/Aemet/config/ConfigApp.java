package Ficheros.Aemet.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigApp {

    private static final String LOCAL_PATH = System.getProperty("user.dir") + File.separator +
            "src" + File.separator +
            "main" + File.separator +
            "java" + File.separator +
            "Ficheros" + File.separator +
            "Aemet" + File.separator;

    public static String APP_DATA = "";

    public static void init() {
        loadProperties();
        initStorage();
    }

    private static void loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = ConfigApp.class.getResourceAsStream("/config.properties")) {
            properties.load(input);

            APP_DATA = properties.getProperty("app.storage.dir", "data");
            APP_DATA = LOCAL_PATH + APP_DATA;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initStorage() {
        // Crear el directorio data si no existe
        Path path = Paths.get(APP_DATA);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

