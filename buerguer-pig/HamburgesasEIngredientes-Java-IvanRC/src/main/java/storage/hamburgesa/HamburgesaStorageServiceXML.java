package storage.hamburgesa;

import config.ConfigApp;
import dto.hamburgesa.ListaHamburgesaDto;
import dto.ingrediente.ListaIngredienteDto;
import mapper.HamburgesaMapper;
import models.Hamburgesa;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HamburgesaStorageServiceXML implements HamburgesaStorageService{

    ConfigApp configApp = ConfigApp.getInstance();
    File file = new File(configApp.getAPP_DATA()+File.separator+"haburgesa.xml");

    public HamburgesaStorageServiceXML() throws IOException {}

    Persister persister = new Persister();

    @Override
    public void saveAll(List<Hamburgesa> entities) throws Exception {
        try {
            persister.write(HamburgesaMapper.toDto(entities), file);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Hamburgesa> loadAll() throws Exception {
        List<Hamburgesa> lista = new ArrayList<>();
        if(!file.exists()) return lista;
        try{
            lista = HamburgesaMapper.toHamburgesas(persister.read(ListaHamburgesaDto.class, file));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return lista;
    }
}
