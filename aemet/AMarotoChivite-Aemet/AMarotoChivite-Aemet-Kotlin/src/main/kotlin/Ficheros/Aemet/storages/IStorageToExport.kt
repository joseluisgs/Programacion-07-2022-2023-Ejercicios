package Ficheros.Aemet.storages

interface IStorageToExport<Model> {
    fun saveInFileWithFilter(toList: List<Model>)
}