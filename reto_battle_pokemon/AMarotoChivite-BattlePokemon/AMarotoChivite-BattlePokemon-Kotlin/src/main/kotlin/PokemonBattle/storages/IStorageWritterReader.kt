package PokemonBattle.storages

interface IStorageWritterReader<Model> {
    fun saveFile(items: List<Model>)
    fun readFile(): List<Model>
}