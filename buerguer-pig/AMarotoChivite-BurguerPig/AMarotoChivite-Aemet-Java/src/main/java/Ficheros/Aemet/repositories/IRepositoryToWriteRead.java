package Ficheros.Aemet.repositories;

import java.util.List;

public interface IRepositoryToWriteRead<Model> {
    void saveFile();

    List<Model> readFile();
}
