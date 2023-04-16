package PokemonBattle.repositories

interface IRepositoryWritterReader<Model> {
    fun saveFile()
    fun readFile(): List<Model>
}