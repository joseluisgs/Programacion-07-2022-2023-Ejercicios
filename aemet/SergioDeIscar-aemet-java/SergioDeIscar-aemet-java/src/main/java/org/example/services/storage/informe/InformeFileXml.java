package org.example.services.storage.informe;

import org.example.config.AppConfig;
import org.example.dto.InformeDto;
import org.example.dto.InformesDto;
import org.example.models.Informe;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.List;

public class InformeFileXml implements InformeStorageService {
    // Singleton
    private static InformeFileXml instance = null;
    public static InformeFileXml getInstance() {
        if (instance == null) {
            instance = new InformeFileXml();
        }
        return instance;
    }

    private final String localFile = AppConfig.getInstance().getAppData() + File.separator + "informes.xml";

    @Override
    public List<Informe> saveAll(List<Informe> elements) {
        File file = new File(localFile);
        if (file.exists() && !file.canWrite() ) return null;
        Persister persister = new Persister();
        try {
            persister.write(
                    new InformesDto(
                            elements.stream().map(Informe::toDto).toList()
                    ), file
            );
            return elements;
        } catch (Exception e) {
            System.out.println("Error al escribir en el fichero xml");
        }
        return null;
    }

    @Override
    public List<Informe> loadAll() {
        File file = new File(localFile);
        if(!file.exists() || !file.canRead()) return null;
        Persister persister = new Persister();
        try {
            InformesDto dto = persister.read(InformesDto.class, file);
            return dto.getInformes().stream().map(InformeDto::toClass).toList();
        } catch (Exception e) {
            System.out.println("Error al leer en el fichero json");
        }
        return null;
    }
}
