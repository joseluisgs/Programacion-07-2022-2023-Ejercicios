package Ficheros.HerenciasDto.storages;

import java.util.List;

public interface IStorageToWriteRead<Model> {
    void saveFile(List<Model> repo);

    List<Model> readFile();
}
