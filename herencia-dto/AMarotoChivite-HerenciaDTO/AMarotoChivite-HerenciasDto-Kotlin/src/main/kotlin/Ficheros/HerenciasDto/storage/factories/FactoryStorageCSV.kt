package Ficheros.HerenciasDto.storage.factories

import Ficheros.HerenciasDto.repositories.Repository
import Ficheros.HerenciasDto.storage.StorageCSV

class FactoryStorageCSV {
    fun create(): Repository {
        return Repository(StorageCSV())
    }
}