package Ficheros.Accidentes.repositories;

import java.util.List;

public interface IRepositoryToReadAndLecture<Model> {
    void saveAllModelsInFile();

    List<Model> readAllModelsInFile();
}
