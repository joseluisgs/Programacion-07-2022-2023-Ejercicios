package storage.registros;

import config.ConfigApp;
import dto.InformesDto;
import mapper.RegistrosMapper;
import models.Registro;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RegistroStorageServiceXml {

    ConfigApp configApp = ConfigApp.getInstance();
    File file = new File(configApp.getAPP_DATA()+File.separator+"registros.xml");

    Persister persister = new Persister();

    public RegistroStorageServiceXml() throws IOException {}

    public void saveAll(List<Map<LocalDate, Registro>> entities) {
        try {
            persister.write(RegistrosMapper.toDto(entities), file);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public List<Map<LocalDate, Registro>> loadAll() throws FileNotFoundException {
        List<Map<LocalDate, Registro>> registros = Collections.emptyList();
        if(!file.exists()) return registros;
        try {
            registros = RegistrosMapper.toRegistros(persister.read(InformesDto.class, file));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return registros;
    }
}
