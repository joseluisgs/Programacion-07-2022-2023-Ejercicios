package Ficheros.Accidentes.controllers;

import Ficheros.Accidentes.models.dto.AccidenteDto;
import Ficheros.Accidentes.repositories.IRepositoryToReadAndLecture;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ControllerPrincipal implements IRepositoryToReadAndLecture<AccidenteDto> {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(ControllerPrincipal.class);
    private final IRepositoryToReadAndLecture<AccidenteDto> repositoryToExportImport;

    public ControllerPrincipal(IRepositoryToReadAndLecture<AccidenteDto> repositoryToExportImport) {
        this.repositoryToExportImport = repositoryToExportImport;
    }

    @Override
    public List<AccidenteDto> readAllModelsInFile() {
        logger.debug("Controller: Leyendo de fichero");
        return repositoryToExportImport.readAllModelsInFile();
    }

    @Override
    public void saveAllModelsInFile() {
        logger.debug("Controller: Escribiendo en fichero");
        repositoryToExportImport.saveAllModelsInFile();
    }
}
