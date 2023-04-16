package Ficheros.Accidentes.storages

interface IStorageToImportExport<Model> {
    fun saveInFileWithFilter()
    fun readAllModelsInFile(): List<Model>
}