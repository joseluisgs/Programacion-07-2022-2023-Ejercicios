package Ficheros.Aemet.repositories;

import Ficheros.Aemet.models.Aemet;
import Ficheros.Aemet.storages.IStorageToWrite;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RepositoryToWrite implements IRepositoryToWrite {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(RepositoryToWrite.class);
    private final IStorageToWrite<Aemet> storage;
    private List<Aemet> repository;

    public RepositoryToWrite(IStorageToWrite<Aemet> storage) {
        this.storage = storage;
        this.repository = List.of();
    }

    public void saveItems(List<Aemet> list) {
        this.repository = list;
    }

    @Override
    public void saveFile() {
        logger.debug("Repository: Escribiendo");
        storage.saveFileWithFilter(repository);
    }

    public void saveInFileDecisionProvincia(String provincia) {
        logger.debug("Repository: Escribiendo fichero para la provincia " + provincia);

        // Filtro del informe que requerimos por provincia
        List<Aemet> filterRepository = repository.stream().filter(aemet -> aemet.getNombreProvincia().equals(provincia)).toList();
        storage.saveFileWithFilter(filterRepository);
    }
}
