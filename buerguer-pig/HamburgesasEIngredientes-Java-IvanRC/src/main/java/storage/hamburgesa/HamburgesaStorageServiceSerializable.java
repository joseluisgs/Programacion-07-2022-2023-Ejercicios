package storage.hamburgesa;

import config.ConfigApp;
import models.Hamburgesa;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HamburgesaStorageServiceSerializable implements HamburgesaStorageService {

    ConfigApp configApp = ConfigApp.getInstance();

    File file = new File(configApp.getAPP_DATA()+File.separator+"hamburgesa.ser");

    public HamburgesaStorageServiceSerializable() throws IOException {}

    @Override
    public void saveAll(List<Hamburgesa> entities) throws IOException {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))){
            objectOutputStream.writeObject(entities);
        }
    }

    @Override
    public List<Hamburgesa> loadAll() throws IOException {
        List<Hamburgesa> hamburgesas = new ArrayList<>();
        if(!file.exists()) return hamburgesas;
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
            hamburgesas = (List<Hamburgesa>) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return hamburgesas;
    }
}
