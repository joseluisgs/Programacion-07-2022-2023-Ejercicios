package org.example.config;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class AppConfig {
    // Singleton
    private static AppConfig instance = null;
    public static AppConfig getInstance() {
        initStorage();
        loadProperties();
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    private String APP_NAME = "Herencia DTO";
    private String APP_VERSION = "1.0.0";
    private static String APP_AUTHOR;
    private static String APP_DATA;
    private static String LOCAL_PATH = System.getProperty("user.dir") + File.separator;

    private static void initStorage(){
        try {
            Files.createDirectories(Paths.get("data"));
        }catch (Exception e){
            System.out.println("Error al crear el directorio de almacenamiento");
        }
    }

    private static void loadProperties(){
        Properties properties = new Properties();
        try {
            properties.load(AppConfig.class.getResourceAsStream("/config.properties"));
            APP_AUTHOR = properties.getProperty("app.author");
            APP_DATA = properties.getProperty("app.storage.dir");
            APP_DATA = LOCAL_PATH + APP_DATA;
        }catch (Exception e){
            System.out.println("Error al cargar las propiedades");
        }
    }

    public String getAppName() {
        return APP_NAME;
    }
    public String getAppVersion() {
        return APP_VERSION;
    }
    public String getAppAuthor() {
        return APP_AUTHOR;
    }
    public String getAppData() {
        return APP_DATA;
    }
}
