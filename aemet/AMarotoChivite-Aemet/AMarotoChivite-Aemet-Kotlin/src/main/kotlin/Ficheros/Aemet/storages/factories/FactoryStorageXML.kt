package Ficheros.Aemet.storage.factories

import Ficheros.Aemet.repositories.RepositoryToExport
import Ficheros.Aemet.storages.StorageXML

class FactoryStorageXML {
    fun create(): RepositoryToExport {
        return RepositoryToExport(StorageXML())
    }
}