package service.storage

interface StorageService<T> {
    fun saveAll(items: List<T>)
    fun loadAll(): List<T>
}