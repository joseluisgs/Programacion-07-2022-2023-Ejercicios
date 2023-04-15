package Ficheros.Aemet.repositories;

import Ficheros.Aemet.models.Aemet;
import Ficheros.Aemet.storages.IStorageToWriteRead;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RepositoryToWriteRead implements IRepositoryToWriteRead<Aemet> {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(RepositoryToWriteRead.class);
    private final IStorageToWriteRead<Aemet> storage;
    private List<Aemet> repository;

    public RepositoryToWriteRead(IStorageToWriteRead<Aemet> storage) {
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

    @Override
    public List<Aemet> readFile() {
        logger.debug("Repository: Leyendo");
        return storage.readFile();
    }
}
