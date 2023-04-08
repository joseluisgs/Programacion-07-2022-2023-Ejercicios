package Ficheros.Accidentes.repositories

interface IRepositoryToImportExport<Model> {
    fun saveAllModelsInFile()
    fun readAllModelsInFile(): List<Model>
}