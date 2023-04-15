package repository.base

interface CrudRepository<T, ID> {
    fun findById(id: ID): T?
    fun getAll(): List<T>
    fun add(entity: T): T?
    fun delete(id: ID): T?
    fun update(entity: T): T?
}