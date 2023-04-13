package storage.personas;

import config.ConfigApp;
import dto.ListaPersonasDto;
import mapper.PersonaMapper;
import models.Persona;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonaStorageServiceXml implements PersonasStorageService{

    ConfigApp configApp = ConfigApp.getInstance();
    File file = new File(configApp.getAPP_DATA()+File.separator+"personas.xml");

    Persister persister = new Persister();

    public PersonaStorageServiceXml() throws IOException {}

    @Override
    public void saveAll(List<Persona> entities) {
        try {
            persister.write(PersonaMapper.toPersonasDto(entities), file);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Persona> loadAll() throws FileNotFoundException {
        List<Persona> personas = new ArrayList<>();
        if(!file.exists()) return personas;
        try {
            personas = PersonaMapper.toPersonas(persister.read(ListaPersonasDto.class, file));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return personas;
    }
}
