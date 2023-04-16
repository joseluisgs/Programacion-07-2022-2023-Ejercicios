package storage.service

interface StorageService <T>{
    fun loadAll(): List<T>
    fun saveAll(items: List<T>)
}