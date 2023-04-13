package storage.medicion;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import config.ConfigApp;
import dto.MapaMedicionesDto;
import mapper.MedicionesMapper;
import models.Medicion;

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

public class MedicionStorageServiceJson {

    ConfigApp configApp = ConfigApp.getInstance();

    File file = new File(configApp.getAPP_DATA()+File.separator+"mediciones.json");

    Moshi moshi = new Moshi.Builder().build();
    JsonAdapter<MapaMedicionesDto> jsonAdapter = moshi.adapter(MapaMedicionesDto.class);

    public MedicionStorageServiceJson() throws IOException {}

    public void saveAll(Map<LocalDate, List<Medicion>> entities) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            bw.write((jsonAdapter.indent("   ").toJson(MedicionesMapper.toMedicionesDto(entities))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<LocalDate, List<Medicion>> loadAll() {
        Map<LocalDate, List<Medicion>> mediciones = Collections.emptyMap();
        if(!file.exists()) return mediciones;
        try{
            String json = Files.readString(Path.of(file.getPath()));
            mediciones = MedicionesMapper.toMediciones(jsonAdapter.fromJson(json));
        }catch (IOException e){
            System.out.println("Error de lectura en ingrediente.json.");
        }
        return mediciones;
    }
}
