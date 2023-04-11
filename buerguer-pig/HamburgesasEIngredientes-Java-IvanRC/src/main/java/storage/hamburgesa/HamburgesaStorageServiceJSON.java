package storage.hamburgesa;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import config.ConfigApp;
import dto.hamburgesa.HamburgesaDto;
import mapper.HamburgesaMapper;
import models.Hamburgesa;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class HamburgesaStorageServiceJSON implements HamburgesaStorageService{


    ConfigApp configApp = ConfigApp.getInstance();

    File file = new File(configApp.getAPP_DATA()+File.separator+"hamburgesa.json");

    Moshi moshi = new Moshi.Builder()
            .build();

    Type type = Types.newParameterizedType(List.class, HamburgesaDto.class);
    JsonAdapter<List<HamburgesaDto>> jsonAdapter = moshi.adapter(type);

    public HamburgesaStorageServiceJSON() throws IOException {}

    @Override
    public void saveAll(List<Hamburgesa> entities) throws IOException {
        String json = jsonAdapter.indent("   ").toJson(HamburgesaMapper.toDto(entities).hamburgesasDto);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file.getPath()))){
            bw.write(json);
        }catch (IOException e){
            System.out.println("Error de escritura en hamburgesa.json.");
        }
    }

    @Override
    public List<Hamburgesa> loadAll() throws IOException {
        List<Hamburgesa> lista = new ArrayList<>();
        if(!file.exists()) return lista;
        try{
            String json = Files.readString(Path.of(file.getPath()));
            lista = jsonAdapter.fromJson(json).stream().map(h -> HamburgesaMapper.toHamburgesa(h)).toList();
        }catch (IOException e){
            System.out.println("Error de lectura en hamburgesa.json.");
        }
        return lista;
    }
}
