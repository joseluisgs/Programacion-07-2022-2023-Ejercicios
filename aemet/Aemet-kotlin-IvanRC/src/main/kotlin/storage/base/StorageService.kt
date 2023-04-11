package storage.base

interface StorageService<T> {
    fun saveAll(entities: List<T>)
    fun loadAll(): List<T>
}