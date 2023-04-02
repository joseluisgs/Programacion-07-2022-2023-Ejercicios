package org.example.services.storage.dupla;

import org.example.config.AppConfig;
import org.example.dto.DuplaDto;
import org.example.dto.DuplasDto;
import org.example.models.Dupla;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DuplaFileXml implements DuplaStorageService{
    //Singleton
    private static DuplaFileXml instance = null;
    public static DuplaFileXml getInstance() {
        if (instance == null) instance = new DuplaFileXml();
        return instance;
    }

    private final String localPath = AppConfig.getInstance().getAppData() + File.separator + "duplas.xml";

    @Override
    public List<Dupla> saveAll(List<Dupla> elements) {
        File file = new File(localPath);
        if (file.exists() && !file.canWrite() ) return null;
        Persister persister = new Persister();
        try {
            persister.write(new DuplasDto(
                elements.stream().map(Dupla::toDto).toList()
            ), file);
            return elements;
        }catch (Exception e) {
            System.out.println("Error al escribir en el fichero XML");
        }
        return null;
    }

    @Override
    public List<Dupla> loadAll() {
        File file = new File(localPath);
        if(!file.exists() || !file.canRead()) return null;
        Persister persister = new Persister();
        try{
            return persister.read(DuplasDto.class, file)
                .getDuplas()
                .stream()
                .map(DuplaDto::toClass)
                .toList();
        } catch (Exception e) {
            System.out.println("Error al leer en el fichero XML");
        }
        return null;
    }
}
