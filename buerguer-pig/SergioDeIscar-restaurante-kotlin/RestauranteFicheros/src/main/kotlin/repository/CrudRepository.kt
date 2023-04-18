package repository

interface CrudRepository<T, ID> {
    fun findAll(): Iterable<T>
    fun findById(id: ID): T?
    fun save(element: T, storage: Boolean = true): T
    fun saveAll(elements: Iterable<T>, storage: Boolean = true)
    fun deleteById(id: ID): Boolean
    fun delete(element: T): Boolean
    fun deleteAll()
    fun existsById(id: ID): Boolean
}