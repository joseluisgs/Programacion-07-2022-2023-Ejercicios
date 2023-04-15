package Ficheros.Aemet.storages;

import java.util.List;

public interface IStorageToWriteRead<Model> {
    void saveFileWithFilter(List<Model> listItems);

    List<Model> readFile();
}

