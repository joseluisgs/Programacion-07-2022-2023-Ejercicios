package storage.accidentes;

import com.squareup.moshi.Moshi;
import config.ConfigApp;
import dto.conjuntoDeAccidentes.ConjuntosDeAccidentesDto;
import mapper.accidente.AccidenteMapper;
import model.Accidente;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AccidenteStorageServiceJson implements AccidenteStorageService{

    ConfigApp configApp = ConfigApp.getInstance();

    File file = new File(configApp.getAPP_DATA()+File.separator+"accidentes.json");

    Moshi moshi = new Moshi.Builder().build();

    public AccidenteStorageServiceJson() throws IOException {}

    @Override
    public void saveAll(List<Accidente> entities) throws Exception {
        try(BufferedWriter br = new BufferedWriter(new FileWriter(file))){
            br.write(moshi.adapter(ConjuntosDeAccidentesDto.class).indent("   ").toJson(new ConjuntosDeAccidentesDto(AccidenteMapper.toAccidentesDto(entities))));
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<Accidente> loadAll() throws Exception {
        List<Accidente> lista = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            lista = AccidenteMapper.toAccidentes(moshi.adapter(ConjuntosDeAccidentesDto.class).fromJson(Files.readString(Path.of(file.getPath()))).getAccidentesDto());
        }catch (Exception e){
            throw e;
        }
        return lista;
    }
}
