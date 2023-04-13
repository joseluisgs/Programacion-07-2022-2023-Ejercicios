package org.example.services.storage.persona;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.example.config.AppConfig;
import org.example.dto.PersonaDto;
import org.example.dto.PersonasDto;
import org.example.models.Persona;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;

public class PersonaFileJson implements PersonaStorageService{
    // Singleton
    private static PersonaFileJson instance = null;
    public static PersonaFileJson getInstance() {
        if (instance == null) {
            instance = new PersonaFileJson();
        }
        return instance;
    }

    private final String localFile = AppConfig.getInstance().getAppData() + File.separator + "personas.json";
    private final Moshi moshi = new Moshi.Builder().build();
    @Override
    public List<Persona> saveAll(List<Persona> elements) {
        File file = new File(localFile);
        if (file.exists() && !file.canWrite() ) return null;
        JsonAdapter<PersonasDto> jsonAdapter = moshi.adapter(PersonasDto.class);
        try(PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            pw.println(
                    jsonAdapter.indent("\t").toJson(
                        new PersonasDto(
                            elements.stream().map(Persona::toDto).toList()
                        )
                    )
            );
        }catch (IOException e){
            System.out.println("Error al escribir en el fichero");
        }
        return elements;
    }

    @Override
    public List<Persona> loadAll() {
        File file = new File(localFile);
        if(!file.exists() || !file.canRead()) return null;
        JsonAdapter<PersonasDto> jsonAdapter = moshi.adapter(PersonasDto.class);
        try {
            String json = Files.readString(file.toPath());
            PersonasDto dto = jsonAdapter.fromJson(json);
            return dto.getPersonas().stream().map(PersonaDto::toClass).toList();
        }catch (IOException e){
            System.out.println("Error al leer en el fichero");
        }
        return null;
    }
}
