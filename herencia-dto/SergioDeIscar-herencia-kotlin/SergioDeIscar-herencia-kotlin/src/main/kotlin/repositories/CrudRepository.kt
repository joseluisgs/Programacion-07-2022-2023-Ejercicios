package repositories

interface CrudRepository<T,ID> {
    fun findAll(): Iterable<T>
    fun findById(id: ID): T?
    fun save(element: T): T
    fun saveAll(elements: Iterable<T>)
    fun deleteById(id: ID): Boolean
    fun delete(element: T): Boolean
    fun deleteAll()
    fun existsById(id: ID): Boolean
}