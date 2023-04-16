package Ficheros.HerenciasDto.repositories;

import Ficheros.HerenciasDto.models.dto.PersonDTO;
import Ficheros.HerenciasDto.models.factories.PersonFactory;
import Ficheros.HerenciasDto.storages.IStorageToWriteRead;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RepositoryToWriteRead implements IRepositoryToWriteRead<PersonDTO> {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(RepositoryToWriteRead.class);
    private final IStorageToWriteRead<PersonDTO> storage;

    PersonFactory factory = new PersonFactory();

    private final List<PersonDTO> repository = factory.createPersonas(20);

    public RepositoryToWriteRead(IStorageToWriteRead<PersonDTO> storage) {
        this.storage = storage;
    }

    @Override
    public void saveFile() {
        logger.debug("Repository: Escribiendo en fichero");
        storage.saveFile(repository);
    }

    @Override
    public List<PersonDTO> readFile() {
        logger.debug("Repository: Leyendo de fichero");
        return storage.readFile();
    }
}
