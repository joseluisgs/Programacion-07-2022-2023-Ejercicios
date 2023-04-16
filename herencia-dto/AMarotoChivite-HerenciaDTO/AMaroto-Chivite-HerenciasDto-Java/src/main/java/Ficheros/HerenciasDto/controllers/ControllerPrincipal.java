package Ficheros.HerenciasDto.controllers;

import Ficheros.HerenciasDto.models.dto.PersonDTO;
import Ficheros.HerenciasDto.repositories.IRepositoryToWriteRead;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ControllerPrincipal {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(ControllerPrincipal.class);
    private final IRepositoryToWriteRead<PersonDTO> repositoryToWriteRead;

    public ControllerPrincipal(IRepositoryToWriteRead<PersonDTO> repositoryToWriteRead) {
        this.repositoryToWriteRead = repositoryToWriteRead;
    }

    public List<PersonDTO> readFile() {
        logger.debug("Controller: Leyendo de fichero");
        return repositoryToWriteRead.readFile();
    }

    public void saveFile() {
        logger.debug("Controller: Escribiendo en fichero");
        repositoryToWriteRead.saveFile();
    }
}
