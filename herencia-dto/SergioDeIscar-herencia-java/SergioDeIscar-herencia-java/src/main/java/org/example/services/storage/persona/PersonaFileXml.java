package org.example.services.storage.persona;

import org.example.config.AppConfig;
import org.example.dto.PersonaDto;
import org.example.dto.PersonasDto;
import org.example.models.Persona;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.List;

public class PersonaFileXml implements PersonaStorageService{
    //Singleton
    private static PersonaFileXml instance = null;
    public static PersonaFileXml getInstance() {
        if (instance == null) {
            instance = new PersonaFileXml();
        }
        return instance;
    }

    private final String localFile = AppConfig.getInstance().getAppData() + File.separator + "personas.xml";

    @Override
    public List<Persona> saveAll(List<Persona> elements) {
        File file = new File(localFile);
        if (file.exists() && !file.canWrite() ) return null;
        Persister serializer = new Persister();
        try {
            serializer.write(new PersonasDto(elements.stream().map(Persona::toDto).toList()), file);
            return elements;
        } catch (Exception e) {
            System.out.println("Error al escribir en el fichero");
        }
        return null;
    }

    @Override
    public List<Persona> loadAll() {
        File file = new File(localFile);
        if(!file.exists() || !file.canRead()) return null;
        try {
            PersonasDto personasDto = new Persister().read(PersonasDto.class, file);
            return personasDto.getPersonas().stream().map(PersonaDto::toClass).toList();
        } catch (Exception e) {
            System.out.println("Error al leer en el fichero");
        }
        return null;
    }
}
