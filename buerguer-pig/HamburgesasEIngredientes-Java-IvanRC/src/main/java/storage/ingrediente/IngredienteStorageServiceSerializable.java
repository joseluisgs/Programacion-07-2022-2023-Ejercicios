package storage.ingrediente;

import config.ConfigApp;
import models.Ingrediente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IngredienteStorageServiceSerializable implements IngredienteStorageService {

    ConfigApp configApp = ConfigApp.getInstance();

    File file = new File(configApp.getAPP_DATA()+File.separator+"ingrediente.ser");

    public IngredienteStorageServiceSerializable() throws IOException {}

    @Override
    public void saveAll(List<Ingrediente> entities) throws IOException {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))){
            objectOutputStream.writeObject(entities);
        }
    }

    @Override
    public List<Ingrediente> loadAll() throws IOException {
        List<Ingrediente> ingredientes = new ArrayList<>();
        if(!file.exists()) return ingredientes;
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
            ingredientes = (List<Ingrediente>) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ingredientes;
    }
}
