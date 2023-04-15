package storage.base;

import java.io.IOException;
import java.util.List;

public interface StorageService<T> {
    void saveAll(List<T> entities) throws Exception;
    List<T> loadAll() throws Exception;
}
