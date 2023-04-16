package Ficheros.BurguerPig.repositories;

import Ficheros.BurguerPig.models.Burguer;
import Ficheros.BurguerPig.models.factories.BurguerFactory;
import Ficheros.BurguerPig.storages.IStorageToWriteRead;
import Ficheros.BurguerPig.storages.StorageXml;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RepositoryToWriteRead implements IRepositoryToWriteRead<Burguer> {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(RepositoryToWriteRead.class);
    private final IStorageToWriteRead<Burguer> storage;
    private final BurguerFactory factory = new BurguerFactory();
    private List<Burguer> repository = factory.create();

    public RepositoryToWriteRead(IStorageToWriteRead<Burguer> storage) {
        this.storage = storage;
    }

    @Override
    public void saveFile() {
        logger.debug("Repository: Escribiendo");
        storage.saveFile(repository);
    }

    @Override
    public List<Burguer> readFile() {
        logger.debug("Repository: Leyendo");
        return storage.readFile();
    }
}
