package Ficheros.Aemet.controllers;

import Ficheros.Aemet.models.Aemet;
import Ficheros.Aemet.repositories.RepositoryToWrite;

import java.util.List;

public class ControllerToWrite {
    private final RepositoryToWrite repositoryToWrite;

    public ControllerToWrite(RepositoryToWrite repositoryToWrite) {
        this.repositoryToWrite = repositoryToWrite;
    }

    public void saveInRepository(List<Aemet> list) {
        repositoryToWrite.saveItems(list);
    }

    public void saveInFileDecisionProvincia(String provincia) {
        repositoryToWrite.saveInFileDecisionProvincia(provincia);
    }
}
