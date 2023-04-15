package org.example.repositories;

import java.util.List;

public interface IExternalStore<T> {
    List<T> upgrade();
    List<T> downgrade();
}
