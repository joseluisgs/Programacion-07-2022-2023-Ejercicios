package Ficheros.Aemet.repositories

interface IRepositoryToImportExport<Model> {
    fun saveAllModelsInFileOverride()
    fun readAllModelsInFile(): List<Model>
}