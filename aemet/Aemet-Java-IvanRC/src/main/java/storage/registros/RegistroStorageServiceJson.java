package storage.registros;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import config.ConfigApp;
import dto.InformesDto;
import mapper.RegistrosMapper;
import models.Registro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RegistroStorageServiceJson {

    ConfigApp configApp = ConfigApp.getInstance();

    File file = new File(configApp.getAPP_DATA()+File.separator+"registros.json");

    Moshi moshi = new Moshi.Builder().build();
    JsonAdapter<InformesDto> jsonAdapter = moshi.adapter(InformesDto.class);

    public RegistroStorageServiceJson() throws IOException {}

    public void saveAll(List<Map<LocalDate, Registro>> entities) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            bw.write((jsonAdapter.indent("   ").toJson(RegistrosMapper.toDto(entities))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Map<LocalDate, Registro>> loadAll() {
        List<Map<LocalDate, Registro>> registros = Collections.emptyList();
        if(!file.exists()) return registros;
        try{
            String json = Files.readString(Path.of(file.getPath()));
            registros = RegistrosMapper.toRegistros(jsonAdapter.fromJson(json));
        }catch (IOException e){
            System.out.println("Error de lectura en registros.json.");
        }
        return registros;
    }
}
