package service.storage

interface StorageService<T> {
    fun saveAll(elements: List<T>): List<T>
    fun loadAll(): List<T>
}