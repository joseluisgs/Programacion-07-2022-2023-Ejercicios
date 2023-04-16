package Ficheros.HerenciasDto.storage.factories

import Ficheros.HerenciasDto.repositories.Repository
import Ficheros.HerenciasDto.storage.StorageGSON_JSON

class FactoryStorageGSON_JSON {
    @ExperimentalStdlibApi
    fun create(): Repository {
        return Repository(StorageGSON_JSON())
    }
}