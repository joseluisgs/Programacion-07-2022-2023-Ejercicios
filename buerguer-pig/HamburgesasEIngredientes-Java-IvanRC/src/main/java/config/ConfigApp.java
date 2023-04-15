package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigApp {
    private static ConfigApp instance = null;

    public static ConfigApp getInstance() throws IOException {
        if(instance == null){
            instance = new ConfigApp();
            loadProperties();
            iniciarStorageService();
        }
        return instance;
    }

    static String APP_NAME;
    static String APP_VERSION;
    static String APP_AUTHOR;
    static String APP_DATA;
    static String LOCAL_PATH = System.getProperty("user.dir") + File.separator;

    public String getAPP_NAME() {
        return APP_NAME;
    }

    public String getAPP_VERSION() {
        return APP_VERSION;
    }

    public String getAPP_AUTHOR() {
        return APP_AUTHOR;
    }

    public String getAPP_DATA() {
        return APP_DATA;
    }

    public String getLOCAL_PATH() {
        return LOCAL_PATH;
    }

    public ConfigApp() {}

    private static void iniciarStorageService() throws IOException {
        File file = new File(APP_DATA);
        if(!file.exists()){
            Files.createDirectory(Paths.get(APP_DATA));
        }
    }

    private static void loadProperties() throws IOException {

        String configPath = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();

        Properties properties = new Properties();
        properties.load(new FileInputStream(configPath));


        APP_NAME = properties.getProperty("APP_NAME");
        if(APP_NAME == null){
            APP_NAME = "Hamburgesas-e-ingredientes-Java";
        }
        APP_VERSION = properties.getProperty("APP_VERSION");
        if(APP_VERSION == null){
            APP_VERSION = "1.0.0";
        }
        APP_AUTHOR = properties.getProperty("APP_AUTHOR");
        if(APP_AUTHOR == null){
            APP_AUTHOR = "IvanRoncoCebadera";
        }
        APP_DATA = properties.getProperty("APP_DATA");
        if(APP_DATA == null){
            APP_DATA = "data";
        }
        APP_DATA = LOCAL_PATH+APP_DATA;
    }

}
