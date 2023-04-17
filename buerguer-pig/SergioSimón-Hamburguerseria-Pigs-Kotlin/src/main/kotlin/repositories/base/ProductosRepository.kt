package repositories.base

interface ProductosRepository<T, ID, Y> {
    fun findAll(): List<T>
    fun findById(id: ID): T?
    fun save(entity: T): T
    fun clear(): Y
}