package repositories

interface IExternalStore<T> {
    fun upgrade(): Iterable<T>
    fun downgrade(): Iterable<T>
}