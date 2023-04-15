package Ficheros.Accidentes.repositories;

import Ficheros.Accidentes.models.dto.AccidenteDto;
import Ficheros.Accidentes.storages.IStorageToWriteRead;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RepositoryToWriteRead implements IRepositoryToReadAndLecture<AccidenteDto> {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(RepositoryToWriteRead.class);
    private final IStorageToWriteRead<AccidenteDto> storage;

    public RepositoryToWriteRead(IStorageToWriteRead<AccidenteDto> storage) {
        this.storage = storage;
    }

    @Override
    public void saveAllModelsInFile() {
        logger.debug("Repository: Escribiendo en fichero");
        storage.saveInFileWithFilter();
    }

    @Override
    public List<AccidenteDto> readAllModelsInFile() {
        logger.debug("Repository: Leyendo de fichero");
        return storage.readAllModelsInFile();
    }
}
