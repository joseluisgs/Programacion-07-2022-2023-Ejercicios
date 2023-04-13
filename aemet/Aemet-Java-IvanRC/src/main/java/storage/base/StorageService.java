package storage.base;

import java.io.FileNotFoundException;
import java.util.List;

public interface StorageService<T>{
    void saveAll(List<T> entities) throws Exception;
    List<T> loadAll() throws FileNotFoundException;
}
