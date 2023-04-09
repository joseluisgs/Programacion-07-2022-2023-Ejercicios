package repositories

interface CrudRepository<T, ID> {
    fun findById(id: ID): T?
    fun findAll(): List<T>
    fun save(): T


}