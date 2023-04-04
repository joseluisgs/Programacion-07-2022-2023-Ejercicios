package org.example.services.storage.dupla;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import okio.BufferedSource;
import org.example.config.AppConfig;
import org.example.dto.DuplaDto;
import org.example.dto.DuplasDto;
import org.example.models.Dupla;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;

public class DuplaFileJson implements DuplaStorageService{
    //Singleton
    private static DuplaFileJson instance = null;
    public static DuplaFileJson getInstance() {
        if (instance == null) instance = new DuplaFileJson();
        return instance;
    }

    private final String localFile = AppConfig.getInstance().getAppData() + File.separator + "duplas.json";
    private final Moshi moshi = new Moshi.Builder().build();

    @Override
    public List<Dupla> saveAll(List<Dupla> elements) {
        File file = new File(localFile);
        if (file.exists() && !file.canWrite() ) return null;
        JsonAdapter<DuplasDto> jsonAdapter = moshi.adapter(DuplasDto.class);
        try(PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            pw.println(
                    jsonAdapter.indent("\t").toJson(
                            new DuplasDto(
                                    elements.stream().map(Dupla::toDto).toList()
                            )
                    )
            );
        }catch (IOException e){
            System.out.println("Error al escribir en el fichero json");
        }

        return elements;
    }

    @Override
    public List<Dupla> loadAll() {
        File file = new File(localFile);
        if(!file.exists() || !file.canRead()) return null;
        JsonAdapter<DuplasDto> jsonAdapter = moshi.adapter(DuplasDto.class);
        /*try {
            String json = Files.readString(file.toPath());
            DuplasDto dto = jsonAdapter.fromJson(json);
            return dto.getDuplasDto().stream().map(DuplaDto::toClass).toList();
        }catch (IOException e){
            System.out.println("Error al leer en el fichero");
        }*/
        try {
            BufferedSource source = okio.Okio.buffer(okio.Okio.source(file));
            DuplasDto dto = jsonAdapter.fromJson(source);
            return dto.getDuplas().stream().map(DuplaDto::toClass).toList();
        } catch (IOException e) {
            System.out.println("Error al leer en el fichero");
        }
        return null;
    }
}
