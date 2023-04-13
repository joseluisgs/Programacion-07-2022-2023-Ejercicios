package repository

interface CrudRepository<T, ID> {
    fun getAll(): List<T>
    fun getById(id: ID): T?
    fun save(element: T): T
    fun saveAll(elements: List<T>)
    fun deleteById(id: ID): T?
    fun update(element: T): T?
    fun updateById(id: ID, element: T): T?

    fun upgrade(): List<T>
    fun downgrade(): List<T>
}