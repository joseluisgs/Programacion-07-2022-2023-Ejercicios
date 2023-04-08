package Ficheros.Aemet.storages

interface IStorageToImportExport<Model> {
    fun saveInFileWithFilter(toList: List<Model>)
    fun readAllModelsInFile(): List<Model>
}