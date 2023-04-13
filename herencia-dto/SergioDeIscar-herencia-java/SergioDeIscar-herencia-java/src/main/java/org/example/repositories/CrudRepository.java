package org.example.repositories;

import java.util.List;

public interface CrudRepository<T, ID> {
    List<T> getAll();
    T getById(ID id);
    T save(T element, boolean storage);
    void saveAll(List<T> elements, boolean storage);
    T deleteById(ID id);
    T update(T element);
    T updateById(ID id, T element);
}
