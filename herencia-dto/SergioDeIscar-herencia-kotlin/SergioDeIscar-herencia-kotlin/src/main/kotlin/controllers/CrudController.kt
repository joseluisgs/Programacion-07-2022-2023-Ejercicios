package controllers

import com.github.michaelbull.result.Result

interface CrudController<T, ID, ERR> {
    fun findAll(): Iterable<T>
    fun findById(id: ID): Result<T, ERR>
    fun save(element: T, storage: Boolean = false): Result<T, ERR>
    fun saveAll(elements: Iterable<T>, storage: Boolean = false)
    fun deleteById(id: ID): Result<Boolean, ERR>
    fun delete(element: T): Result<Boolean, ERR>
    fun deleteAll()
    fun existsById(id: ID): Result<Boolean, ERR>

    fun exportData()
    fun importData()
}