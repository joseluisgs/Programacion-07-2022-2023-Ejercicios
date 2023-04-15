package Ficheros.Aemet.storages;

import java.util.List;

public interface IStorageToWrite<Model> {
    void saveFileWithFilter(List<Model> listItems);
}

