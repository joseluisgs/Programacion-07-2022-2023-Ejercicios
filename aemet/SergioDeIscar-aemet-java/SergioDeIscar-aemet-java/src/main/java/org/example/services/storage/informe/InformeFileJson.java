package org.example.services.storage.informe;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.example.config.AppConfig;
import org.example.dto.InformeDto;
import org.example.dto.InformesDto;
import org.example.models.Informe;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class InformeFileJson implements InformeStorageService {
    //Singleton
    private static InformeFileJson instance = null;
    public static InformeFileJson getInstance() {
        if (instance == null) instance = new InformeFileJson();
        return instance;
    }

    private final String localFile = AppConfig.getInstance().getAppData() + File.separator + "informes.json";
    private final Moshi moshi = new Moshi.Builder().build();

    @Override
    public List<Informe> saveAll(List<Informe> elements) {
        File file = new File(localFile);
        if (file.exists() && !file.canWrite() ) return null;
        JsonAdapter<InformesDto> jsonAdapter = moshi.adapter(InformesDto.class);
        try(PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            pw.println(
                    jsonAdapter.indent("\t").toJson(
                            new InformesDto(
                                    elements.stream().map(Informe::toDto).toList()
                            )
                    )
            );
            return elements;
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero json");
        }
        return null;
    }

    @Override
    public List<Informe> loadAll() {
        File file = new File(localFile);
        if(!file.exists() || !file.canRead()) return null;
        JsonAdapter<InformesDto> jsonAdapter = moshi.adapter(InformesDto.class);
        try {
            String json = Files.readString(file.toPath());
            InformesDto dto = jsonAdapter.fromJson(json);
            return dto.getInformes().stream().map(InformeDto::toClass).toList();

        } catch (IOException e) {
            System.out.println("Error al leer en el fichero json");
        }
        return null;
    }
}
