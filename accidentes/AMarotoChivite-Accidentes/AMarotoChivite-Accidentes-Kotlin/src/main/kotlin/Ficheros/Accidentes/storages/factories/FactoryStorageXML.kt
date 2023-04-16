package Ficheros.Accidentes.storage.factories

import Ficheros.Accidentes.repositories.RepositoryToImportExport
import Ficheros.Accidentes.storages.StorageXML

class FactoryStorageXML {
    fun create(): RepositoryToImportExport {
        return RepositoryToImportExport(StorageXML())
    }
}