package storage.personas;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import config.ConfigApp;
import dto.ListaPersonasDto;
import mapper.PersonaMapper;
import models.Persona;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PersonaStorageServiceJson implements PersonasStorageService{

    ConfigApp configApp = ConfigApp.getInstance();

    File file = new File(configApp.getAPP_DATA()+File.separator+"personas.json");

    Moshi moshi = new Moshi.Builder().build();
    JsonAdapter<ListaPersonasDto> jsonAdapter = moshi.adapter(ListaPersonasDto.class);

    public PersonaStorageServiceJson() throws IOException {}

    @Override
    public void saveAll(List<Persona> entities) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            bw.write((jsonAdapter.indent("   ").toJson(PersonaMapper.toPersonasDto(entities))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Persona> loadAll() {
        List<Persona> personas = new ArrayList<>();
        if(!file.exists()) return personas;
        try{
            String json = Files.readString(Path.of(file.getPath()));
            personas = PersonaMapper.toPersonas(jsonAdapter.fromJson(json));
        }catch (IOException e){
            System.out.println("Error de lectura en ingrediente.json.");
        }
        return personas;
    }
}
