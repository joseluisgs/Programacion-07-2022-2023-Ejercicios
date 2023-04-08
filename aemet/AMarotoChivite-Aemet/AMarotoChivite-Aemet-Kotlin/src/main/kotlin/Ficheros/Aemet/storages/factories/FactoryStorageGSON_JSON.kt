package Ficheros.Aemet.storage.factories

import Ficheros.Aemet.repositories.RepositoryToExport
import Ficheros.Aemet.storages.StorageGSON_JSON

class FactoryStorageGSON_JSON {
    @ExperimentalStdlibApi
    fun create(): RepositoryToExport {
        return RepositoryToExport(StorageGSON_JSON())
    }
}