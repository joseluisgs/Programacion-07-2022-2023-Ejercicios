package Ficheros.Aemet.controllers;

import Ficheros.Aemet.models.Aemet;
import Ficheros.Aemet.repositories.RepositoryToWriteRead;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ControllerPrincipal {

    private final RepositoryToWriteRead repositoryToWriteRead;
    private final Logger logger = (Logger) LoggerFactory.getLogger(ControllerPrincipal.class);

    public ControllerPrincipal(RepositoryToWriteRead repositoryToWriteRead) {
        this.repositoryToWriteRead = repositoryToWriteRead;
    }

    public void saveInRepository(List<Aemet> list) {
        logger.debug("Controller: Guardando items en repository");
        repositoryToWriteRead.saveItems(list);
    }

    public void saveInFile() {
        logger.debug("Controller: Escribiendo (sobreescribiendo)");
        repositoryToWriteRead.saveFile();
    }

    public List<Aemet> readInFile() {
        logger.debug("Controller: Leyendo");
        return repositoryToWriteRead.readFile();
    }

}
