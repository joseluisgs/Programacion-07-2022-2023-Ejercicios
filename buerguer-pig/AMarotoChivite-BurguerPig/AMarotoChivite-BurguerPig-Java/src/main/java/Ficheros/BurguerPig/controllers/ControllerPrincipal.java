package Ficheros.BurguerPig.controllers;

import Ficheros.BurguerPig.models.Burguer;
import Ficheros.BurguerPig.repositories.IRepositoryToWriteRead;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ControllerPrincipal {

    private final IRepositoryToWriteRead repositoryToWriteRead;
    private final Logger logger = (Logger) LoggerFactory.getLogger(ControllerPrincipal.class);

    public ControllerPrincipal(IRepositoryToWriteRead repositoryToWriteRead) {
        this.repositoryToWriteRead = repositoryToWriteRead;
    }

    public void saveInFile() {
        logger.debug("Controller: Escribiendo (sobreescribiendo)");
        repositoryToWriteRead.saveFile();
    }

    public List<Burguer> readInFile() {
        logger.debug("Controller: Leyendo");
        return repositoryToWriteRead.readFile();
    }

}
