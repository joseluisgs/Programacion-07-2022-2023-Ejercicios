package Ficheros.Accidentes.storages;

import java.util.List;

public interface IStorageToWriteRead<Model> {
    void saveInFileWithFilter();

    List<Model> readAllModelsInFile();
}

