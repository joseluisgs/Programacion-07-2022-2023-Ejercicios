package storage.ingrediente;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import config.ConfigApp;
import models.Ingrediente;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class IngredienteStorageServiceJSON implements IngredienteStorageService{

    ConfigApp configApp = ConfigApp.getInstance();

    File file = new File(configApp.getAPP_DATA()+File.separator+"ingrediente.json");

    public IngredienteStorageServiceJSON() throws IOException {}

    Moshi moshi = new Moshi.Builder().build();

    Type type = Types.newParameterizedType(List.class, Ingrediente.class);
    JsonAdapter<List<Ingrediente>> jsonAdapter = moshi.adapter(type);

    @Override
    public void saveAll(List<Ingrediente> entities) throws IOException {
        String json = jsonAdapter.indent("   ").toJson(entities);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file.getPath()))){
            bw.write(json);
        }catch (IOException e){
            System.out.println("Error de escritura en ingrediente.json.");
        }
    }

    @Override
    public List<Ingrediente> loadAll() throws IOException {
        List<Ingrediente> lista = new ArrayList<>();
        if(!file.exists()) return lista;
        try{
            String json = Files.readString(Path.of(file.getPath()));
            lista = (List<Ingrediente>) jsonAdapter.fromJson(json);
        }catch (IOException e){
            System.out.println("Error de lectura en ingrediente.json.");
        }
        return lista;
    }
}
