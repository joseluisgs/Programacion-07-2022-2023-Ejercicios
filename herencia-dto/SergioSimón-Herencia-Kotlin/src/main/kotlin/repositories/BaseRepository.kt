package repositories

interface BaseRepository<T> {
    fun findAll(): List<T>
    fun save(entity: T): T
}