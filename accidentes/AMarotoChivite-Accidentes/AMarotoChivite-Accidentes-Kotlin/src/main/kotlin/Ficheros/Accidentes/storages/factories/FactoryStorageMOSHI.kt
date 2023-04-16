package Ficheros.Accidentes.storage.factories

import Ficheros.Accidentes.repositories.RepositoryToImportExport
import Ficheros.Accidentes.storages.StorageMOSHI_JSON

@ExperimentalStdlibApi
class FactoryStorageMOSHI {
    fun create(): RepositoryToImportExport {
        return RepositoryToImportExport(StorageMOSHI_JSON())
    }
}