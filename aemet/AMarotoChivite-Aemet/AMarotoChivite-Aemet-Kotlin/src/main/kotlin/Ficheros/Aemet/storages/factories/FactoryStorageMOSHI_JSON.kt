package Ficheros.Aemet.storage.factories

import Ficheros.Aemet.repositories.RepositoryToExport
import Ficheros.Aemet.storages.StorageMOSHI_JSON

class FactoryStorageMOSHI_JSON {
    @ExperimentalStdlibApi
    fun create(): RepositoryToExport {
        return RepositoryToExport(StorageMOSHI_JSON())
    }
}