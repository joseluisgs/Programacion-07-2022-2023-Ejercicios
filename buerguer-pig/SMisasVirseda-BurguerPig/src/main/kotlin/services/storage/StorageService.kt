package services.storage

interface StorageService<T> {
    fun cargar(): List<T>
    fun guardar(items: List<T>)
}