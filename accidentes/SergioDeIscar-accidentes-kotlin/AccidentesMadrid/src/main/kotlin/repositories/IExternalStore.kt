package repositories

interface IExternalStore<T> {
    fun upgrade(): List<T>
    fun downgrade(): List<T>
}