package Ficheros.Accidentes.storage.factories

import Ficheros.Accidentes.repositories.RepositoryToImportExport
import Ficheros.Accidentes.storages.StorageGSON_JSON

class FactoryStorageGSON {
    fun create(): RepositoryToImportExport {
        return RepositoryToImportExport(StorageGSON_JSON())
    }
}