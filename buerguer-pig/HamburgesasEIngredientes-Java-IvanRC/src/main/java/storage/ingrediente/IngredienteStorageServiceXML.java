package storage.ingrediente;

import config.ConfigApp;
import dto.ingrediente.ListaIngredienteDto;
import mapper.IngredienteMapper;
import models.Ingrediente;
import org.simpleframework.xml.core.Persister;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IngredienteStorageServiceXML implements IngredienteStorageService{

    ConfigApp configApp = ConfigApp.getInstance();
    File file = new File(configApp.getAPP_DATA()+File.separator+"ingrediente.xml");

    public IngredienteStorageServiceXML() throws IOException {
    }

    Persister persister = new Persister();

    @Override
    public void saveAll(List<Ingrediente> entities) throws Exception {
        try {
            persister.write(IngredienteMapper.toDto(entities), file);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Ingrediente> loadAll() throws Exception {
        List<Ingrediente> lista = new ArrayList<>();
        if(!file.exists()) return lista;
        try{
            lista = IngredienteMapper.toIngredientes(persister.read(ListaIngredienteDto.class, file));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return lista;
    }
}
