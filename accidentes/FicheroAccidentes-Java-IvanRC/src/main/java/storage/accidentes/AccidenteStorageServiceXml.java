package storage.accidentes;


import config.ConfigApp;
import dto.accidente.ListaAccidentesDto;
import mapper.accidente.AccidenteMapper;
import model.Accidente;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccidenteStorageServiceXml implements AccidenteStorageService{

    ConfigApp configApp = ConfigApp.getInstance();
    File file = new File(configApp.getAPP_DATA()+File.separator+"accidentes.xml");

    Persister persister = new Persister();

    public AccidenteStorageServiceXml() throws IOException {}

    @Override
    public void saveAll(List<Accidente> entities) {
        try {
            persister.write(AccidenteMapper.toAccidentesDto(entities), file);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Accidente> loadAll() {
        List<Accidente> lista = new ArrayList<>();
        if(!file.exists()) return lista;
        try{
            lista = AccidenteMapper.toAccidentes(persister.read(ListaAccidentesDto.class, file));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return lista;
    }
}
