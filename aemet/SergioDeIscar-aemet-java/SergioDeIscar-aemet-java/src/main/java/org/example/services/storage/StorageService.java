package org.example.services.storage;

import java.util.List;

public interface StorageService<T> {
    List<T> saveAll(List<T> elements);
    List<T> loadAll();
}
