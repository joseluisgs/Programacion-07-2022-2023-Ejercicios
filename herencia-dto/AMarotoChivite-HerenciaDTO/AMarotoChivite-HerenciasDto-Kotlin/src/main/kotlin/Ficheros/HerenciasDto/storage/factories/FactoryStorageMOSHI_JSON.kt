package Ficheros.HerenciasDto.storage.factories

import Ficheros.HerenciasDto.repositories.Repository
import Ficheros.HerenciasDto.storage.StorageMOSHI_JSON

class FactoryStorageMOSHI_JSON {
    @ExperimentalStdlibApi
    fun create(): Repository {
        return Repository(StorageMOSHI_JSON())
    }
}