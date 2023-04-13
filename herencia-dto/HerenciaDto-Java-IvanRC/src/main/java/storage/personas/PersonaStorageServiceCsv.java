package storage.personas;

import config.ConfigApp;
import dto.PersonaDto;
import mapper.PersonaMapper;
import models.Persona;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaStorageServiceCsv implements PersonasStorageService{

    ConfigApp configApp = ConfigApp.getInstance();

    File file = new File(configApp.getAPP_DATA()+File.separator+"personas.csv");

    public PersonaStorageServiceCsv() throws IOException {}

    @Override
    public void saveAll(List<Persona> entities) {
        try(BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            br.write("nombre;edad;modulo;tipo\n");
            entities.stream().map(p -> PersonaMapper.toDto(p)).forEach(persona ->
                    {
                        try {
                            br.append(
                                    persona.getNombre()+";"+persona.getEdad()+";"+persona.getModulo()+";"+persona.getTipo()+"\n"
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
    public List<Persona> loadAll() throws FileNotFoundException {
        List<Persona> ingredientes = new ArrayList<>();
        if(!file.exists()) return ingredientes;
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] campos = line.split(";");
                ingredientes.add(PersonaMapper.toPersona(new PersonaDto(campos[0], campos[1], campos[2], campos[3])));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ingredientes;
    }
}
