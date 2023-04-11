package storage.ingrediente;

import config.ConfigApp;
import models.Ingrediente;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class IngredienteStorageServiceTxt implements IngredienteStorageService{

    ConfigApp configApp = ConfigApp.getInstance();

    File file = new File(configApp.getAPP_DATA()+File.separator+"ingrediente.txt");

    public IngredienteStorageServiceTxt() throws IOException {
    }

    @Override
    public void saveAll(List<Ingrediente> entities) throws Exception {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            bw.write("");
            entities.forEach(ingrediente ->
            {
                try {
                    bw.write(String.valueOf(ingrediente.getId()));
                    bw.newLine();
                    bw.write(ingrediente.getNombre());
                    bw.newLine();
                    bw.write(String.valueOf(ingrediente.getPrecio()));
                    bw.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            );
        }
    }

    @Override
    public List<Ingrediente> loadAll() throws Exception {
        List<Ingrediente> ingredientes = new ArrayList<>();
        if(!file.exists()) return ingredientes;
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            while(br.ready()) {
                ingredientes.add(
                        new Ingrediente(
                                Integer.parseInt(br.readLine()),
                                br.readLine(),
                                Double.parseDouble(br.readLine())
                        )
                );
            }
        }
        return ingredientes;
    }
}
