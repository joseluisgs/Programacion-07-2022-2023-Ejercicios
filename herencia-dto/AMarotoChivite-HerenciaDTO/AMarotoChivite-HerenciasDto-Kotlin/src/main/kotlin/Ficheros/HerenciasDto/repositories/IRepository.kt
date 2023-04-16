package Ficheros.HerenciasDto.repositories

interface IRepository<Model> {
    fun saveAllModelsInFileOverride()
    fun readAllModelsInFile(): List<Model>
}