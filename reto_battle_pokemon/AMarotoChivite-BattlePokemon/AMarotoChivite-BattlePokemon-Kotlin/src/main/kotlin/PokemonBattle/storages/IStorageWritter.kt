package PokemonBattle.storages

interface IStorageWritter<Model> {
    fun saveFile(items: List<Model>)
}