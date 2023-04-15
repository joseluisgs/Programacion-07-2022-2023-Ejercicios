package repository.base;


import java.io.IOException;
import java.util.List;

public interface CrudRepository<T, ID> {
    List<T> getAll() throws Exception;
    T getById(ID id) throws Exception;
    T add(T entity) throws Exception;
    T update(T entity);
    T remove(ID id);
}
