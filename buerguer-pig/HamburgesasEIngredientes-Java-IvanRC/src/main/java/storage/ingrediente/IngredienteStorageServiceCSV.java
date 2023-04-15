package storage.ingrediente;

import config.ConfigApp;
import models.Ingrediente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IngredienteStorageServiceCSV implements IngredienteStorageService{

    ConfigApp configApp = ConfigApp.getInstance();

    File file = new File(configApp.getAPP_DATA()+File.separator+"ingrediente.csv");

    public IngredienteStorageServiceCSV() throws IOException {
    }

    @Override
    public void saveAll(List<Ingrediente> entities) {
        try(BufferedWriter br = new BufferedWriter(new FileWriter(file.getPath()))) {
            br.write("id,nombre,precio\n");
            entities.forEach(ingrediente ->
                    {
                        try {
                            br.append(
                                    ingrediente.getId()+","+ingrediente.getNombre()+","+ ingrediente.getPrecio()+"\n"
                            );
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ingrediente> loadAll() {
        List<Ingrediente> ingredientes = new ArrayList<>();
        if(!file.exists()) return ingredientes;
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] campos = line.split(",");
                ingredientes.add(new Ingrediente(Integer.parseInt(campos[0]), campos[1], Double.parseDouble(campos[2])));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ingredientes;
    }
}
