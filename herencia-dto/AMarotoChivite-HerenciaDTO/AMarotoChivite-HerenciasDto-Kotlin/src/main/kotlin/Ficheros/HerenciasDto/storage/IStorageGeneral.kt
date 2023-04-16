package Ficheros.HerenciasDto.storage

interface IStorageGeneral <Model> {
    fun saveInFile(toList: List<Model>)
    fun readAllModelsInFile(): List<Model>
}