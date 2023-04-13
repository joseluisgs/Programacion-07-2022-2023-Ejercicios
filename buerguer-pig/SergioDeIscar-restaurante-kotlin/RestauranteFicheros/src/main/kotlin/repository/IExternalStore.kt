package repository

interface IExternalStore<T> {
    fun upgrade(): List<T>
    fun downgrade(): List<T>
}