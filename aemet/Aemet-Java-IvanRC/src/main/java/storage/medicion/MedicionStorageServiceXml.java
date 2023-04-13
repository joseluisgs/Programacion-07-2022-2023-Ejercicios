package storage.medicion;

import config.ConfigApp;
import dto.ListaMedicionesDto;
import dto.MapaMedicionesDto;
import mapper.MedicionesMapper;
import models.Medicion;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MedicionStorageServiceXml {

    ConfigApp configApp = ConfigApp.getInstance();
    File file = new File(configApp.getAPP_DATA()+File.separator+"mediciones.xml");

    Persister persister = new Persister();

    public MedicionStorageServiceXml() throws IOException {}

    public void saveAll(Map<LocalDate, List<Medicion>> entities) {
        try {
            persister.write(MedicionesMapper.toMedicionesDto(entities), file);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public Map<LocalDate, List<Medicion>> loadAll() throws FileNotFoundException {
        Map<LocalDate, List<Medicion>> mediciones = Collections.emptyMap();
        if(!file.exists()) return mediciones;
        try {
            mediciones = MedicionesMapper.toMediciones(persister.read(MapaMedicionesDto.class, file));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return mediciones;
    }
}
