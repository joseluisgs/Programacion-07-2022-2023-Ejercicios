package storage.ingrediente;

import config.ConfigApp;
import models.Ingrediente;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IngredienteStorageServiceBinario implements IngredienteStorageService{

    ConfigApp configApp = ConfigApp.getInstance();

    File file = new File(configApp.getAPP_DATA()+File.separator+"ingrediente.bin");

    public IngredienteStorageServiceBinario() throws IOException {
    }

    @Override
    public void saveAll(List<Ingrediente> entities) throws Exception {
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)){
            entities.forEach(ingrediente -> {
                    try {
                        fileOutputStream.write((ingrediente.getId()+"\n").getBytes());
                        fileOutputStream.write((ingrediente.getNombre()+"\n").getBytes());
                        fileOutputStream.write((ingrediente.getPrecio()+"\n").getBytes());
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
            }
            );
        }
    }

    @Override
    public List<Ingrediente> loadAll() throws Exception {
        List<Ingrediente> ingredientes = new ArrayList<>();
        if(!file.exists()) return ingredientes;
        try(FileInputStream fileInputStream = new FileInputStream(file)){
            char c = ' ';

            do {
                StringBuilder id = new StringBuilder();
                if(c != ' ') {
                    do {
                        id.append(c);
                    }while((c = (char) fileInputStream.read()) != '\n');
                }else{
                    do{
                        c = (char) fileInputStream.read();
                        id.append(c);
                    }while ((c = (char) fileInputStream.read()) != '\n');
                }

                StringBuilder nombre = new StringBuilder();
                while ((c = (char) fileInputStream.read()) != '\n') {
                    nombre.append((char) c);
                }

                StringBuilder precio = new StringBuilder();
                while ((c = (char) fileInputStream.read()) != '\n') {
                    precio.append((char) c);
                }

                ingredientes.add(
                        new Ingrediente(
                                Integer.parseInt(String.valueOf(id)),
                                nombre.toString(),
                                Double.parseDouble(precio.toString())
                        )
                );
                c = (char) fileInputStream.read();
            }while(c != '\uFFFF');
        }
        return ingredientes;
    }
}
